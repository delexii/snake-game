package com.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Array;

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
        this.texture = texture;
    }

    public Texture texture;
    public Texture tailUp;

    public BodyPart(Snake game) {
        this.game = game;
        this.texture = new Texture("SnakeBody.jpg");
        this.tailUp = new Texture("Tail.jpg");

    }

    public void updateBodyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {

        game.batch.draw(texture, x, y, 60, 60);
    }

    public void drawTail() {

        game.batch.draw(tailUp, x, y, 60, 60);

    }

}
