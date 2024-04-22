package com.arcomage.screen;

import com.arcomage.Game;
import com.arcomage.core.Event;
import com.arcomage.core.EventListener;
import com.arcomage.core.EventManager;
import com.arcomage.core.ScreenEvent;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends AbstractScreen implements EventListener {
    private Stage stage;
    private Skin skin;

    public MainMenuScreen(Game game) {
        super(game);
        EventManager.register(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());  // Ensure the stage is using a ScreenViewport.
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));  // Ensure this file is correctly formatted and exists.

        // Creating the layout
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Adding buttons
        TextButton playButton = new TextButton("Play", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Add buttons to table
        table.add(playButton).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton).fillX().uniformX();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void handleEvent(Event event) {
        if(event.getName() == ScreenEvent.PLAY_GAME) {
            game.setScreen(new GameScreen(game));
        }

        if(event.getName() == ScreenEvent.EXIT_GAME) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
