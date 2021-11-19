package com.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BodyPart {

    final Snake game;

    private int x;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;
    private Texture texture;

    public BodyPart(Snake game) {
        this.game = game;
        this.texture = new Texture("SnakeBody.jpg");
    }

    public void updateBodyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
            game.batch.draw(texture, x, y, 60, 60);
    }

}
