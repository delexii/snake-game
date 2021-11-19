package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Apple {
    private Texture texture;
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
        this.texture = new Texture("Apple.jpg");
    }

    public void draw() {game.batch.draw(texture, appleX, appleY, 60, 60); }

    public void setX(int SNAKE_MOVEMENT){
        this.appleX = MathUtils.random(Gdx.graphics.getWidth() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
    }

    public void setY(int SNAKE_MOVEMENT){
        this.appleY = MathUtils.random(Gdx.graphics.getHeight() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
    }


}

