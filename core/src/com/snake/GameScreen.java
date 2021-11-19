package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    final Snake game;
    // rectangle and images for snakehead
    Rectangle snakehead;
    private Texture imgUp;
    private Texture imgDown;
    private Texture imgLeft;
    private Texture imgRight;
    private Array<BodyPart> bodyParts = new Array<BodyPart>();

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

    int snakeX = 1920 / 2 - 60;
    int snakeY = 1080 / 2 - 60;
    public boolean appleIsOnScreen = false;
    private Apple apple1;



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
        // Temporary bodypart adder
        addBodyPart();
        addBodyPart();
        addBodyPart();
        addBodyPart();
    }

    @Override
    public void render(float delta) {
        // timer function to control render speed
        timer -= delta;
        if (timer <= 0) {
            timer = MOVE_TIME;
            moveSnake();
        }
        ScreenUtils.clear(0, 0, 0, 1);


        game.batch.begin();
        drawSnake();
        addApple();
        game.batch.end();
        userInput();

        //EndGameScreen when the snake touch the wall
        renderEndGameScreen();

    }

    // get user input
    private void userInput() {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (lPressed) snakeDirection = LEFT;
        if (rPressed) snakeDirection = RIGHT;
        if (uPressed) snakeDirection = UP;
        if (dPressed) snakeDirection = DOWN;
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

        checkEdges();
        updateBodyParts();
    }


    public void checkEdges() {
        // keep the circle in the screen
        if (snakeX < 30) snakeX = 30;
        if (snakeX > 1920 - 30) snakeX = 1920 - 30;
        if (snakeY < 30) snakeY = 30;
        if (snakeY > 1080 - 30) snakeY = 1080 - 30;
    }


    public void updateBodyParts(){
        if (bodyParts.size >0) {
        BodyPart bodyPart = bodyParts.removeIndex(0);
        bodyPart.updateBodyPosition(snakeXBeforeUpdate, snakeYBeforeUpdate);
        bodyParts.add(bodyPart);
        }
    }

    private void drawSnake(){
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
        for (BodyPart bodyPart : bodyParts) {
            if (!(bodyPart.getX() == snakeX && bodyPart.getY() == snakeY))
                bodyPart.draw();}
    }

    public void renderEndGameScreen(){
        if (snakeX == 30) game.setScreen(new

                EndGameScreen(game));
        if (snakeX == 1920 - 30) game.setScreen(new

                EndGameScreen(game));
        if (snakeY == 30) game.setScreen(new

                EndGameScreen(game));
        if (snakeY == 1080 - 30) game.setScreen(new

                EndGameScreen(game));
    }

    public void addBodyPart(){
        BodyPart bodyPart = new BodyPart(game);
        bodyPart.updateBodyPosition(snakeX, snakeY);
        bodyParts.insert(0,bodyPart);
    }

    public void addApple() {
        Apple apple = new Apple(game);
        if (appleIsOnScreen == false) {
            randomApple(apple);
        }
        apple1.draw();
        appleIsOnScreen = true;
        if (snakeX == apple1.getX() && snakeY == apple1.getY()){
            appleIsOnScreen = false;
        }
    }

    public void randomApple(Apple apple){
        apple.setX(SNAKE_MOVEMENT);
        apple.setY(SNAKE_MOVEMENT);
        Apple apple1 = apple;
        this.apple1 =  apple1;
        }

    }







