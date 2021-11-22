package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class BodyPart {

    final Snake game;

    private int x, y ;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTexture(Texture texture) {
        this.snakeBody = texture;
    }

    public Texture snakeBody;
    public Texture tailUp;
    public Texture tailDown;
    public Texture tailLeft;
    public Texture tailRight;

    public BodyPart(Snake game) {
        this.game = game;
        this.snakeBody = new Texture("SnakeBody.jpg");
        this.tailUp = new Texture("tailUp.jpg");
        this.tailDown = new Texture("tailDown.jpg");
        this.tailLeft = new Texture("tailLeft.jpg");
        this.tailRight = new Texture("tailRight.jpg");
    }

    public void updateBodyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {

        game.batch.draw(snakeBody, x, y, 60, 60);
    }

    public void drawTail(int directionX, int directionY) {
        // draws tail in correct orientation using coordinates of bodypart before it
        if (this.y < directionY) game.batch.draw(tailUp, x, y, 60, 60);
        if (this.y > directionY) game.batch.draw(tailDown, x, y, 60, 60);
        if (this.x > directionX) game.batch.draw(tailLeft, x, y, 60, 60);
        if (this.x < directionX) game.batch.draw(tailRight, x, y, 60, 60);

    }

}
