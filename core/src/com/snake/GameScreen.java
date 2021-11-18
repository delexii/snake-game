package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {

    final Snake game;

    float circleX = 1920 / 2;
    float circleY = 1080 / 2;
    float circle2X = 1920 / 2;
    float circle2Y = 1080 / 2 - 60;
//	float width = 20;
//	float height = 20;


//    public SpriteBatch batch;
//    public BitmapFont font;

    // Snake movement controls
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    private int snakeDirection = -1;

    // Snake movement time handling
    private static float MOVE_TIME = 1F;
    private float timer = MOVE_TIME;
    private static final int SNAKE_MOVEMENT = 7;

    public GameScreen(Snake  game) {
        this.game = game;
    }

    @Override

    public void render(float delta) {
//		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
//		pixmap.setColor(Color.BLACK);
//		pixmap.fillCircle(x, y, r);
//		Texture texture = new Texture(pixmap);

        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();
//		batch.draw(img, 190, 120);
        game.batch.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(1, 1, 0, 1);
        game.shapeRenderer.circle(circleX, circleY, 30);
        game.shapeRenderer.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0, 1, 1, 1);
        game.shapeRenderer.circle(circle2X, circle2Y, 30);
        game.shapeRenderer.end();
        userInput();

        //EndGameScreen when the snake touch the wall
        if (circleX == 30) game.setScreen(new EndGameScreen(game));
        if (circleX == 1920 - 30) game.setScreen(new EndGameScreen(game));
        if (circleY == 30) game.setScreen(new EndGameScreen(game));
        if (circleY == 1080 - 30) game.setScreen(new EndGameScreen(game));
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
        moveSnake();
    }



    // Move the snake left, right, up or down
    private void moveSnake() {


        // Let the snake move by 8 frames on 1 second
        if (timer <= 0) {
            timer = MOVE_TIME;
            circleX += SNAKE_MOVEMENT;
        }
        switch (snakeDirection) {
            case RIGHT:
                circleX += SNAKE_MOVEMENT;
                tailMove("RIGHT");
                break;

            case LEFT:
                circleX -= SNAKE_MOVEMENT;
                tailMove("LEFT");
                break;

            case UP:
                circleY += SNAKE_MOVEMENT;
                tailMove("UP");
                break;

            case DOWN:
                circleY -= SNAKE_MOVEMENT;
                tailMove("DOWN");
                break;
        }
        checkEdges();
    }

    public void checkEdges() {
        // keep the circle in the screen
        if (circleX < 30) circleX = 30;
        if (circleX > 1920 - 30) circleX = 1920 - 30;
        if (circleY < 30) circleY = 30;
        if (circleY > 1080 - 30) circleY = 1080 - 30;


    }


    // advanced tail move
    private void tailMove(String snakeDirection) {
        if (snakeDirection == "LEFT") {
            if (circleY > circle2Y) circle2Y += SNAKE_MOVEMENT;
            if (circleY < circle2Y) circle2Y -= SNAKE_MOVEMENT;
            if (circle2X - circleX > 60) circle2X -= SNAKE_MOVEMENT;
        }
        if (snakeDirection == "RIGHT") {
            if (circleY > circle2Y) circle2Y += SNAKE_MOVEMENT;
            if (circleY < circle2Y) circle2Y -= SNAKE_MOVEMENT;
            if (circleX - circle2X > 60) circle2X += SNAKE_MOVEMENT;
        }
        if (snakeDirection == "UP") {
            if (circleX > circle2X) circle2X += SNAKE_MOVEMENT;
            if (circleX < circle2X) circle2X -= SNAKE_MOVEMENT;
            if (circleY - circle2Y > 60) circle2Y += SNAKE_MOVEMENT;
        }
        if (snakeDirection == "DOWN") {
            if (circleX > circle2X) circle2X += SNAKE_MOVEMENT;
            if (circleX < circle2X) circle2X -= SNAKE_MOVEMENT;
            if (circle2Y - circleY > 60) circle2Y -= SNAKE_MOVEMENT;
        }

    }


    @Override
    public void resize(int width, int height) {

    }


}
