package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Snake extends Game {
	Texture img;
	ShapeRenderer shapeRenderer;
	float circleX = 200;
	float circleY = 100;

public SpriteBatch batch;
public BitmapFont font;



	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		img = new Texture("badlogic.jpg");
		shapeRenderer = new ShapeRenderer();
		setScreen(new EndGameScreen(this));

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 0, 1);
        shapeRenderer.circle(circleX, circleY, 30);
        shapeRenderer.end();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) circleX -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) circleX += 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) circleY -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) circleY += 200 * Gdx.graphics.getDeltaTime();


		// keep the circle in the screen
		if (circleX < 30) circleX = 30;
		if (circleX > 1920 - 30) circleX = 1920 - 30;
		if (circleY < 30) circleY = 30;
		if (circleY > 1080 - 30) circleY = 1080 - 30;

		//EndGameScreen when the snake touch the wall
		if (circleX == 30) setScreen(new EndGameScreen(this));
		if (circleX == 1920 - 30) setScreen(new EndGameScreen(this));
		if (circleY == 30) setScreen(new EndGameScreen(this));
		if (circleY == 1080 - 30) setScreen(new EndGameScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
