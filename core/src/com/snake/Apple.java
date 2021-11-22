package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Apple {
    private Texture goodApple;
    private Texture rottenApple;
    private Snake game;
    private int appleX;
    private int appleY;

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

    }

    public void drawApple() {game.batch.draw(goodApple, appleX, appleY, 60, 60); }
    public void drawRottenApple() {game.batch.draw(rottenApple, appleX, appleY, 60, 60); }

    public void setX(int SNAKE_MOVEMENT){
        this.appleX = MathUtils.random(Gdx.graphics.getWidth() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
    }

    public void setY(int SNAKE_MOVEMENT){
        this.appleY = MathUtils.random(Gdx.graphics.getHeight() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
    }


}

