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
	float circleX = 1920/2;
	float circleY = 1080/2;
	float circle2X = 1920/2;
	float circle2Y = 1080/2 -60;
//	float width = 20;
//	float height = 20;



//	public Snake () {
//
//	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		shapeRenderer = new ShapeRenderer();
//		Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

	}

	@Override
	public void render () {
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
			tailMove("DOWN");
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			circleY += 200 * Gdx.graphics.getDeltaTime();
			tailMove("UP");
		}

	}


	private void tailMove(String direction) {
		if (direction == "LEFT")  {
			if (circleY > circle2Y) circle2Y += 115 * Gdx.graphics.getDeltaTime();
			if (circleY < circle2Y) circle2Y -= 115 * Gdx.graphics.getDeltaTime();
			if (circle2X - circleX > 60) circle2X -= 200 * Gdx.graphics.getDeltaTime();
		}
		if (direction == "RIGHT") {
			if (circleY > circle2Y) circle2Y += 115 * Gdx.graphics.getDeltaTime();
			if (circleY < circle2Y) circle2Y -= 115 * Gdx.graphics.getDeltaTime();
			if (circleX - circle2X > 60) circle2X += 200 * Gdx.graphics.getDeltaTime();
		}
		if (direction == "UP") {
			if (circleX > circle2X) circle2X += 115 * Gdx.graphics.getDeltaTime();
			if (circleX < circle2X) circle2X -= 115 * Gdx.graphics.getDeltaTime();
			if (circleY - circle2Y > 60) circle2Y += 200 * Gdx.graphics.getDeltaTime();
		}
		if (direction == "DOWN") {
			if (circleX > circle2X) circle2X += 115 * Gdx.graphics.getDeltaTime();
			if (circleX < circle2X) circle2X -= 115 * Gdx.graphics.getDeltaTime();
			if (circle2Y - circleY > 60) circle2Y -= 200 * Gdx.graphics.getDeltaTime();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		shapeRenderer.dispose();
	}
}
