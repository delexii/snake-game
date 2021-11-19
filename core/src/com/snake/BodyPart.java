package com.snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class BodyPart {

    final Snake game;
    boolean lastbodypart = false;

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

    public BodyPart(Snake game) {
        this.game = game;
        this.texture = new Texture("SnakeBody.jpg");
    }

    public void updateBodyPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        GameScreen.getBodyParts();
        if (game.bodyParts.get(bodyParts.size -1).getX() == bodyPart.getX() && bodyParts.get(bodyParts.size -1).getY() == bodyPart.getY() )
            bodyParts.get(bodyParts.size -1).setTexture(tailUp);


        game.batch.draw(texture, x, y, 60, 60);
    }

}
