package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Apple {
    private Texture texture;
    private Snake game;
    public boolean isAvailable = false;
    public int appleX, appleY;

    public Apple(Snake game){
        this.game = game;
        this.texture = new Texture("badlogic.jpg");
    }

    public void draw() {game.batch.draw(texture, appleX, appleY, 60, 60); }

    public Integer getX(int SNAKE_MOVEMENT){
        appleX = MathUtils.random(Gdx.graphics.getWidth() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
        return appleX;
    }

    public Integer getY(int SNAKE_MOVEMENT){
        appleY = MathUtils.random(Gdx.graphics.getHeight() / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
        return appleY;

    }
}

