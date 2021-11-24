package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.audio.Music;

import java.util.Iterator;

public class WelcomeScreen extends ScreenAdapter {

    final  Snake game;
    private Stage stage;
    OrthographicCamera camera;
    // sound
    Music welcomeMusic;
    Rectangle gameTitle;
    Rectangle gameStart;

    Texture gameTitleImage;
    Texture gameStartImage;
    Texture appleImage;
    Texture rottenAppleImage;
    Texture bananaImage;
    Texture randomImage;
    Array<Rectangle> appleDrops;
    Array<Rectangle> bananaDrops;
    Array<Rectangle> rottenAppleDrops;
    long appleDropTime;
    long bananaDropTime;
    long rottenAppleDropTime;


    public WelcomeScreen (Snake game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        // create a stage and set up as an inputprocessor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        welcomeMusic = Gdx.audio.newMusic(Gdx.files.internal("welcome.wav"));
        welcomeMusic.setLooping(true);
        welcomeMusic.setVolume(0.2F);

        gameStartImage= new Texture(Gdx.files.internal("StartGame.png"));
        gameTitleImage = new Texture(Gdx.files.internal("Title.png"));
        appleImage = new Texture(Gdx.files.internal("Apple.png"));
        rottenAppleImage = new Texture(Gdx.files.internal("RottenApple.png"));
        bananaImage = new Texture(Gdx.files.internal("Banana.png"));


//        randomiser();
//        System.out.println(randomImage);

        gameTitle = new Rectangle();
        gameTitle.x = 1920 / 2 - 1000 / 2;
        gameTitle.y = 650;
        gameTitle.width = 1000;
        gameTitle.height = 314;

        gameStart = new Rectangle();
        gameStart.x = 1920 / 2 - 1000 / 2;
        gameStart.y = 50;
        gameStart.width = 1000;
        gameStart.height = 314;

        appleDrops = new Array<Rectangle>();
        spawnAppleDrops();

//        bananaDrops = new Array<Rectangle>();
//        spawnBananaDrops();

//        rottenAppleDrops = new Array<Rectangle>();
//        spawnRottenAppleDrops();

    }


        private void spawnAppleDrops() {
            Rectangle appleDrop = new Rectangle();
            appleDrop.x = MathUtils.random(0, 1920 - 60);
            appleDrop.y = 1080;
            appleDrop.width = 60;
            appleDrop.height = 60;
            randomiser();
//            System.out.println(randomImage);
            appleDrops.add(appleDrop);
            appleDropTime = TimeUtils.nanoTime();
        }


//        private void spawnBananaDrops() {
//            Rectangle bananaDrop = new Rectangle();
//            bananaDrop.x = MathUtils.random(0, 1920 - 60);
//            bananaDrop.y = 1080;
//            bananaDrop.width = 60;
//            bananaDrop.height = 60;
//            randomiser();
//            bananaDrops.add(bananaDrop);
//            bananaDropTime = TimeUtils.nanoTime();
//    }

//    private void spawnRottenAppleDrops() {
//        Rectangle rottenAppleDrop = new Rectangle();
//        rottenAppleDrop.x = MathUtils.random(0, 1920 - 60);
//        rottenAppleDrop.y = 1080;
//        rottenAppleDrop.width = 60;
//        rottenAppleDrop.height = 60;
//        rottenAppleDrops.add(rottenAppleDrop);
//        rottenAppleDropTime = TimeUtils.nanoTime();
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

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        game.batch.begin();
        for (Rectangle appleDrop : appleDrops) {
            game.batch.draw(randomImage, appleDrop.x, appleDrop.y, appleDrop.width , appleDrop.height);
        }
//        for (Rectangle bananaDrop : bananaDrops) {
//            game.batch.draw(randomImage, bananaDrop.x, bananaDrop.y, bananaDrop.width, bananaDrop.height);
//        }
//        for (Rectangle rottenAppleDrop : rottenAppleDrops) {
//            game.batch.draw(rottenAppleImage, rottenAppleDrop.x, rottenAppleDrop.y, rottenAppleDrop.width, rottenAppleDrop.height);
//        }
        game.batch.draw(gameTitleImage, gameTitle.x, gameTitle.y, gameTitle.width, gameTitle.height);
        game.batch.draw(gameStartImage, gameStart.x, gameStart.y, gameStart.width, gameStart.height);
        game.font.draw(game.batch, "COPYRIGHT 2021 Tiger Cubed Games", 1600, 30);
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f ));
        stage.draw();



        if (TimeUtils.nanoTime() - appleDropTime > 1000000000) spawnAppleDrops();
//        if (TimeUtils.nanoTime() - bananaDropTime > 2000000000) spawnBananaDrops();
//        if (TimeUtils.nanoTime() - rottenAppleDropTime > 2000000000) spawnRottenAppleDrops();



        Iterator<Rectangle> iter = appleDrops.iterator();
        while(iter.hasNext()) {
            Rectangle appleDrop = iter.next();
            appleDrop.y -= 70 * Gdx.graphics.getDeltaTime();
            if (appleDrop.y + 60 < 0) iter.remove();
        }
//        Iterator<Rectangle> iter2 = bananaDrops.iterator();
//        while(iter2.hasNext()) {
//            Rectangle bananaDrop = iter2.next();
//            bananaDrop.y -= 50 * Gdx.graphics.getDeltaTime();
//            if (bananaDrop.y + 60 < 0) iter2.remove();
//        }
//        Iterator<Rectangle> iter3 = rottenAppleDrops.iterator();
//        while(iter3.hasNext()) {
//            Rectangle rottenAppleDrop = iter3.next();
//            rottenAppleDrop.y -= 50 * Gdx.graphics.getDeltaTime();
//            if (rottenAppleDrop.y + 60 < 0) iter3.remove();
//        }

    }


    public void randomiser(){
        Texture fruitImages [] = {appleImage, bananaImage};
        int randomIndex = (int)(Math.random()*fruitImages.length);
            randomImage = fruitImages[randomIndex];
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
