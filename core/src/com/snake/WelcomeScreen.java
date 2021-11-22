package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class WelcomeScreen extends ScreenAdapter {

    final  Snake game;
    private Stage stage;
    // sound
    Music welcomeMusic;
    Rectangle gameTitle;
    Texture gameTitleImage;
    Array<Rectangle> appleDrops;


    public WelcomeScreen (Snake game) {
        this.game = game;

        // create a stage and set up as an inputprocessor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        welcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("welcome.wav"));
        welcomeMusic.setLooping(true);

        gameTitleImage = new Texture(Gdx.files.internal("SnakeGameTitle.jpg"));

        gameTitle = new Rectangle();
        gameTitle.x = 1920 / 2 - 1000 / 2;
        gameTitle.y = 650;
        gameTitle.width = 1000;
        gameTitle.height = 314;


//        appleDrops = new Array<Rectangle>();
//        spawnAppleDrops();
    }


//        private void spawnAppleDrops() {
//            Rectangle appleDrop = new Rectangle();
//            appleDrop.x = MathUtils.random(0, 1920 - 60);
//            appleDrop.y = 1080;
//            appleDrop.width = 60;
//            appleDrop.height = 60;
//            appleDrops.add(appleDrop);
//            lastDropTime = TimeUnits.nanoTime();
//    }


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
        table.row().pad(200, 0, 10, 0);
        table.add(newGame).prefHeight(100).prefWidth(300);
        table.row().pad(10,0,10,0);
        table.add(exit).prefHeight(100).prefWidth(300);

        // create button listener
        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
              welcomeMusic.stop();
            }
        });

        exit.addListener(new ChangeListener() {
            @Override

            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        // TO STOP SOUND WHILE TESTING COMMENT OUT BELOW SOUND
//        welcomeMusic.play();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f ));
        stage.draw();


        game.batch.begin();
        game.batch.draw(gameTitleImage, gameTitle.x, gameTitle.y, gameTitle.width, gameTitle.height);
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
      welcomeMusic.dispose();

    }
    @Override
    public void resize(int width, int height) {
      stage.getViewport().update(width, height, true);
    }

}
