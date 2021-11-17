package com.snake;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndGameScreen implements Screen {

    final  Snake game;



    public EndGameScreen(Snake game) {
        this.game = game;
    }


    @Override
    public void render(float delta) {}

    @Override
    public void show() {
        ScreenUtils.clear(1, 0, 0, 1);

        game.batch.begin();
        game.font.draw(game.batch, "Game Over !!", 480, 560);
        game.font.draw(game.batch, "Do you want to play again ??", 480, 500);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
