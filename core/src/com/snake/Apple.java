package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;


public class Apple {
    private Texture goodApple;
    private Texture rottenApple;
    private Texture banana;
    private Snake game;
    private double random;
    private int appleX;
    private Texture texture;
    private int appleY;
    private ArrayList<int[]> snakeLocations;
    public int getX() {
        return appleX;
    }
    public int getY() {
        return appleY;
    }

    public Apple(Snake game){
        this.game = game;
        this.goodApple = new Texture("Apple.jpg");
        this.rottenApple = new Texture("Rotten Apple.jpg");
        this.banana = new Texture("Banana.jpg");
        this.snakeLocations = new ArrayList<>();
    }


    public Texture drawApple() {game.batch.draw(goodApple, appleX, appleY, 60, 60); return goodApple;}
    public Texture drawRottenApple() {game.batch.draw(rottenApple, appleX, appleY, 60, 60); return rottenApple;}
    public Texture drawRandomApple() {
        if (random == 0) {
            drawApple();
            texture = goodApple;
        }if (random == 1) {
            drawRottenApple();
            texture = rottenApple;
        }
        return texture;
    }

    public void random(){
        double random = Math.floor(Math.random() * 2);
        this.random = random;
    }


    public void drawBanana() {game.batch.draw(banana, appleX, appleY, 60, 60); }

    public void setXAndY(int SNAKE_MOVEMENT){
        int randomX = 0;
        int randomY = 0;
        int [] randomArray = {randomX, randomY};
        boolean locationclash = false;

         do {
            randomX = 60 + MathUtils.random((Gdx.graphics.getWidth() - 120) / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
            randomY = 60 + MathUtils.random((Gdx.graphics.getHeight() - 120) / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;

            for (int[] snakeLocation : snakeLocations) {
                if (randomArray == snakeLocation) {
                    locationclash = true;
                }
                else locationclash = false;

            }
        } while (locationclash == true);

            this.appleX = randomX;
            this.appleY = randomY;

    }

//    public void setY(int SNAKE_MOVEMENT){
//        this.appleY = 60 + MathUtils.random((Gdx.graphics.getHeight() - 120) / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
//
//    }

    public void findSnakeCoordinates(int snakeX, int snakeY, Array<BodyPart> bodyParts) {
        snakeLocations.add(new int[] {snakeX, snakeY});
        for(BodyPart bodyPart : bodyParts) {
            snakeLocations.add(new int[]{bodyPart.getX(),bodyPart.getY()});
        }
    };


}

