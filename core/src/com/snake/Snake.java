package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Snake extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shapeRenderer;
	Pixmap pixmap;
	float circleX = 1920 / 2;
	float circleY = 1080 / 2;
	float circle2X = 1920 / 2;
	float circle2Y = 1080 / 2 - 60;
//	float width = 20;
//	float height = 20;

	// Snake movement controls
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
	private static final int UP = 2;
	private static final int DOWN = 3;
	private int snakeDirection = -1;

	// Snake movement time handling
	private static float MOVE_TIME = 1F;
	private float timer = MOVE_TIME;
	private static final int SNAKE_MOVEMENT = 8;


//	public Snake () {
//
//	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shapeRenderer = new ShapeRenderer();
//		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

	}

	@Override
	public void render() {
//		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
//		pixmap.setColor(Color.BLACK);
//		pixmap.fillCircle(x, y, r);
//		Texture texture = new Texture(pixmap);

			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();
//		batch.draw(img, 190, 120);
			batch.end();


			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(1, 1, 0, 1);
			shapeRenderer.circle(circleX, circleY, 30);
			shapeRenderer.end();

			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.setColor(0, 1, 1, 1);
			shapeRenderer.circle(circle2X, circle2Y, 30);
			shapeRenderer.end();
			move();
			userInput();
		}

		public void move() {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				circleX -= 200 * Gdx.graphics.getDeltaTime();
				tailMove("LEFT");
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				circleX += 200 * Gdx.graphics.getDeltaTime();
				tailMove("RIGHT");
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
				circleY -= 200 * Gdx.graphics.getDeltaTime();
				tailMove("UP");
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				circleY += 200 * Gdx.graphics.getDeltaTime();
				tailMove("DOWN");
			}

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
					break;

				case LEFT:
					circleX -= SNAKE_MOVEMENT;
					break;

				case UP:
					circleY += SNAKE_MOVEMENT;
					break;

				case DOWN:
					circleY -= SNAKE_MOVEMENT;
					break;
			}
		}

	private void tailMove(String snakeDirection) {
		if (snakeDirection == "LEFT")  circle2X -= 200 * Gdx.graphics.getDeltaTime();
		if (snakeDirection == "RIGHT")  circle2X += 200 * Gdx.graphics.getDeltaTime();
		if (snakeDirection == "UP")  circle2Y -= 200 * Gdx.graphics.getDeltaTime();
		if (snakeDirection == "DOWN")  circle2Y += 200 * Gdx.graphics.getDeltaTime();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		shapeRenderer.dispose();
	}
}
