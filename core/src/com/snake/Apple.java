package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Apple {
    private Texture goodApple;
    private Texture rottenApple;
    private Snake game;
    private int appleX;
    private int appleY;
    // sound for good apple
    Sound growSound;
    Sound shrinkSound;
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
        growSound = Gdx.audio.newSound(Gdx.files.internal("grow.wav"));
        shrinkSound = Gdx.audio.newSound(Gdx.files.internal("shrink.wav"));
    }

    public void drawApple() {game.batch.draw(goodApple, appleX, appleY, 60, 60); }
    public void drawRottenApple() {game.batch.draw(rottenApple, appleX, appleY, 60, 60); }

    public void setX(int SNAKE_MOVEMENT){
        this.appleX = 60 + MathUtils.random((Gdx.graphics.getWidth() - 120) / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
        growSound.play();
    }

    public void setY(int SNAKE_MOVEMENT){
        this.appleY = 60 + MathUtils.random((Gdx.graphics.getHeight() - 120) / SNAKE_MOVEMENT - 1) * SNAKE_MOVEMENT;
        //shrinkSound.play();
    }


}

