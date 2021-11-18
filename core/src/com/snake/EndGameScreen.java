package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndGameScreen extends ScreenAdapter {

    final  Snake game;



    public EndGameScreen(Snake game) {
        this.game = game;
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        game.batch.begin();
        game.font.draw(game.batch, "Game Over !!", 480, 560);
        game.font.draw(game.batch, "Do you want to play again ??", 480, 500);
        game.font.draw(game.batch, "Press Space if Yes or ESC for Quit", 480, 440);
        game.batch.end();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                } else if(keyCode == Input.Keys.ESCAPE){
                    Gdx.app.exit();
                }
                return true;
            }
       });
    }


    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
