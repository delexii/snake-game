package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameScreen extends ScreenAdapter {

    final Snake game;

    //instance of userinput class to take user inputs
    UserInput userInput = new UserInput();
    // rectangle and images for snakehead
    Rectangle snakehead;
    private Texture imgUp;
    private Texture imgDown;
    private Texture imgLeft;
    private Texture imgRight;

    //bodyParts array and setter
    public Array<BodyPart> bodyParts = new Array<BodyPart>();
    public Array<BodyPart> getBodyParts() {
        return bodyParts;
    }
    public Array<BodyPart> randomArray = new Array<BodyPart>();
    public Array<BodyPart> getRandomArray() {
        return randomArray;
    }

    // sounds
    Music gameMusic;
    Sound growSound;
    Sound shrinkSound;

    // Snake movement controls
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = -1;

    // Snake movement time handling
    private static float MOVE_TIME = 0.1F;
    private float timer = MOVE_TIME;
    private static final int SNAKE_MOVEMENT = 60;
    private int snakeXBeforeUpdate = 0, snakeYBeforeUpdate = 0;

    public int snakeX = 1920 / 2 - 60;
    public int snakeY = 1080 / 2 - 60;
    public boolean appleIsOnScreen = false;
    public boolean rottenAppleIsOnScreen = false;
    public boolean randomAppleIsOnScreen= false;

    private Apple apple1;
    private Apple apple2;
    private Apple apple3;

    // add scores
    int score = 0;


    public GameScreen(Snake game) {
        this.game = game;

        //snakehead rectangle size 60p x 60 p assumes 1080p x 1920p  grid 18 x 32
        snakehead = new Rectangle();
        snakehead.width = 60;
        snakehead.height = 60;

//         Images for snakehead
        imgUp = new Texture("SnakeHeadUp.jpg");
        imgDown = new Texture("SnakeHeadDown.jpg");
        imgLeft = new Texture("SnakeHeadLeft.jpg");
        imgRight = new Texture("SnakeHeadRight.jpg");

        // Sound for game
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game.wav"));
        gameMusic.setLooping(true);
        growSound = Gdx.audio.newSound(Gdx.files.internal("applecrunchwav.wav"));
        shrinkSound = Gdx.audio.newSound(Gdx.files.internal("shrink.wav"));

        //bodyparts of snake at start of game
        addBodyPart();
        addBodyPart();
    }

    @Override
    public void render(float delta) {
        //Get User input
        userInput.userInput(snakeDirection);
        snakeDirection = userInput.snakeDirection;

        // timer function to control render speed
        timer -= delta;
        if (timer <= 0) {
            timer = MOVE_TIME;
            moveSnake();

        }

        // kill snake if it goes off edge
        deathAtEdge();

        // Set Background Colour
        ScreenUtils.clear(0, 0, 0, 1);

        //Batch draw methods
        game.batch.begin();
        drawStartSnake();
        drawSnake();
        addApple();
        addRottenApple();
        addThirdApple();
        game.batch.end();

        // FOR MUSIC TO STOP WHILE TESTING UNCOMMENT THE BELOW LINE OUT
         gameMusic.stop();
    }

    // Move the snake left, right, up or down
    private void moveSnake() {
        snakeXBeforeUpdate = snakeX;
        snakeYBeforeUpdate = snakeY;
        switch (snakeDirection) {
            case RIGHT:
                snakeX += SNAKE_MOVEMENT;
                break;
            case LEFT:
                snakeX -= SNAKE_MOVEMENT;
                break;
            case UP:
                snakeY += SNAKE_MOVEMENT;
                break;
            case DOWN:
                snakeY -= SNAKE_MOVEMENT;
                break;
        }


        // check if snake bites itself and die/remove tail (needs to be here to avoid crash)
//        checkSnakeIntersection();

        // update bodyparts so snake 'moves'
        updateBodyParts();

  
    }

    private void deathAtEdge() {
        //EndGameScreen when the snake touch the wall or too small
        if (snakeX == -60)
            gameOver();
        if (snakeX == 1920 - 120)
            gameOver();
        if (snakeY == -60)
            gameOver();
        if (snakeY == 1080)
            gameOver();
        if (bodyParts.size < 2)
            gameOver();
    }

    private void gameOver() {
        game.setScreen(new EndGameScreen(game));
        gameMusic.stop();
    }

    @Override
    public void show() {
        gameMusic.play();
    }

    @Override
    public void dispose() {
        gameMusic.dispose();
        growSound.dispose();
        shrinkSound.dispose();
    }

    private void updateBodyParts() {
        if (bodyParts.size > 0) {
            BodyPart bodyPart = bodyParts.removeIndex(0);
            if (snakeDirection != -1)
                bodyPart.updateBodyPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
                bodyParts.add(bodyPart);
        }
    }

    private void drawSnake() {
        // draw snakehead in correct orientation
        switch (snakeDirection) {
            case RIGHT:
                game.batch.draw(imgRight, snakeX, snakeY, snakehead.width, snakehead.height);
                break;
            case LEFT:
                game.batch.draw(imgLeft, snakeX, snakeY, snakehead.width, snakehead.height);
                break;
            case UP:
                game.batch.draw(imgUp, snakeX, snakeY, snakehead.width, snakehead.height);
                break;
            case DOWN:
                game.batch.draw(imgDown, snakeX, snakeY, snakehead.width, snakehead.height);
                break;
            case -1:
                game.batch.draw(imgUp, snakeX, snakeY, snakehead.width, snakehead.height);
                break;
        }

        //Bodypart drawing using bodypart and tail draw methods
        if (snakeDirection != -1) {
            int counter = 0;
            for (BodyPart bodyPart : bodyParts) {
                if (!(bodyPart.getX() == snakeX && bodyPart.getY() == snakeY)) {
                    if (counter == 0) {
                        if (bodyParts.size > 1) {
                            int directionX = bodyParts.get(1).getX();
                            int directionY = bodyParts.get(1).getY();
                            bodyPart.drawTail(directionX, directionY);
                        }
                    } else
                        bodyPart.draw();
                    counter += 1;
                }
            }
        }
    }

    private void drawStartSnake() {
        //Draw snake before movement
        if (snakeDirection == -1) {
            bodyParts.get(0).updateBodyPosition(1920/2 -60, 1080/2 -180);
            bodyParts.get(0).drawTail(1920/2 -60, 1080);
            bodyParts.get(1).updateBodyPosition(1920/2 -60, 1080/2 -120);
            bodyParts.get(1).draw();
        }
    }

    public void addBodyPart() {
        BodyPart bodyPart = new BodyPart(game);
        bodyPart.updateBodyPosition(snakeX, snakeY);
        bodyParts.insert(0, bodyPart);
    }

    public void deleteBodyPart() {
        bodyParts.removeIndex(0);
    }

    public void addApple() {
        Apple apple = new Apple(game);
        if (appleIsOnScreen == false) {
            apple.findSnakeCoordinates(snakeX,snakeY, bodyParts);
            randomApple(apple);

        }
        apple1.drawApple();
        appleIsOnScreen = true;
        if (snakeX == apple1.getX() && snakeY == apple1.getY()){
            appleIsOnScreen = false;
            growSound.play();
            addBodyPart();
            score ++;
            System.out.println(score);
        }
    }


    public void randomApple(Apple apple){
        apple.setXAndY(SNAKE_MOVEMENT);
        Apple apple1 = apple;
        this.apple1 =  apple1;
    }


    public void addRottenApple() {
        Apple rottenApple = new Apple(game);
        if (rottenAppleIsOnScreen == false) {
            randomRottenApple(rottenApple);
        }
        apple2.drawRottenApple();
        rottenAppleIsOnScreen = true;
        if (snakeX == apple2.getX() && snakeY == apple2.getY()){
            rottenAppleIsOnScreen = false;
            shrinkSound.play();
            deleteBodyPart();
            score --;
            System.out.println(score);
        }
    }
    public void randomRottenApple(Apple rottenApple){
        rottenApple.setXAndY(SNAKE_MOVEMENT);
        Apple apple2 = rottenApple;
        this.apple2 =  apple2;
    }

    public void addThirdApple() {
        Apple randomApple = new Apple(game);
        if (randomAppleIsOnScreen == false) {
            randomThirdApple(randomApple);
            apple3.random();
        }
        apple3.drawRandomApple();
        randomAppleIsOnScreen = true;

        if (snakeX == apple3.getX() && snakeY == apple3.getY()) {
            randomAppleIsOnScreen = false;
            if (apple3.drawRandomApple() == apple3.drawApple()){
                growSound.play();
                addBodyPart();
                score++;
                System.out.println(score);
            }
            if (apple3.drawRandomApple() == apple3.drawRottenApple()) {
                shrinkSound.play();
                deleteBodyPart();
                score--;
                System.out.println(score);
            }
        }
    }

        private void randomThirdApple(Apple randomApple) {
            randomApple.setXAndY(SNAKE_MOVEMENT);
            Apple apple3 = randomApple;
            this.apple3 = apple3;
        }

    public void checkSnakeIntersection() {
//        int counter = 0;
//        for (BodyPart bodyPart : bodyParts) {
//            if (snakeX == bodyPart.getX() && snakeY == bodyPart.getY()) {
//                bodyParts.removeRange(0, counter);
//            }
//            counter++;
//        }
        int counter = 0;
        for (BodyPart bodyPart : bodyParts) {
            for (int j = 0; j <= counter; j++) {
                if (snakeX == bodyPart.getX() && snakeY == bodyPart.getY()) {
                    BodyPart part = bodyParts.removeIndex(j);
                    randomArray.add(part);
                    bodyParts.removeRange(0, counter);
                }

            }
            counter++;

//        }

        }
    }

//    public void tailFlash(){
//        for (int i = 0; i < 4; i++){
//            for (BodyPart bodypart: randomArray) {
//                bodypart.draw();
//            }
//        }
//    }
}













