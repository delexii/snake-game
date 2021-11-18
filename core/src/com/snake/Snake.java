package com.snake;


import com.badlogic.gdx.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Snake extends Game {
//	Texture img;
	ShapeRenderer shapeRenderer;
	Pixmap pixmap;
	SpriteBatch batch;
	BitmapFont font;

	@Override
	public void create() {
		shapeRenderer = new ShapeRenderer();
//		pixmap = new Pixmap();
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		shapeRenderer.dispose();
//		pixmap.dispose();
		batch.dispose();
		font.dispose();
	}

}

