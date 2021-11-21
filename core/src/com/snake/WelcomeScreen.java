package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class WelcomeScreen extends ScreenAdapter {

    final  Snake game;

    private Stage stage;



    public WelcomeScreen (Snake game) {
        this.game = game;

        // create a stage and set up as an inputprocessor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {
        // create a table that fills the screen. Everything else will go inside the table
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);

        // Button skins
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton exit = new TextButton("Exit", skin);

        //Add buttons to the table
        table.add(newGame).prefHeight(100).prefWidth(300);
        table.row().pad(10,0,10,0);
        table.add(exit).prefHeight(100).prefWidth(300);

        // create button listener
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f ));
        stage.draw();


        game.batch.begin();
        game.font.draw(game.batch, "COPYRIGHT 2021 Tiger Cubed Games", 1600, 20);
        game.batch.end();

    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();

    }
    @Override
    public void resize(int width, int height) {
      stage.getViewport().update(width, height, true);
    }
}
