package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class WelcomeScreen extends ScreenAdapter {

    final  Snake game;

    // sound
    Music welcomeMusic;


    public WelcomeScreen (Snake game) {
        this.game = game;
        welcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("welcome.wav"));
        welcomeMusic.setLooping(true);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.53f,0.81f,0.92f,1 );

        game.batch.begin();
        game.font.draw(game.batch, "WELCOME", 920, 800);
        game.font.draw(game.batch, "to", 950, 760);
        game.font.draw(game.batch, "HUNGRY SNAKE !!", 900, 720);
        game.font.draw(game.batch, "Press Space to Start the game", 880, 600);
        game.font.draw(game.batch, "Press Esc to Quit the game", 880, 570);
        game.font.draw(game.batch, "COPYRIGHT 2021 Tiger Cubed Games", 1600, 20);
        game.batch.end();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game)); {
                        welcomeMusic.stop();
                    }
                } else if(keyCode == Input.Keys.ESCAPE){
                    Gdx.app.exit();
                }
                return true;
            }
        });
        welcomeMusic.play();
    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        welcomeMusic.dispose();
    }

}
