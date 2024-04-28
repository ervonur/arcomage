package com.arcomage.screen;

import com.arcomage.Game;
import com.arcomage.core.Event;
import com.arcomage.core.EventListener;
import com.arcomage.core.EventManager;
import com.arcomage.core.ScreenEvent;
import com.arcomage.entity.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends BaseScreen implements EventListener {
    private Stage stage;
    private Button playButton;
    private Button exitButton;

    public MainMenuScreen(Game game) {
        super(game);
        EventManager.register(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        playButton = new Button("Play",
                Gdx.graphics.getWidth() / 3,
                Gdx.graphics.getHeight() / 2,
                Gdx.graphics.getWidth() / 8,
                Gdx.graphics.getHeight() / 6,
                Color.GREEN
                );
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(playButton);
        Gdx.input.setInputProcessor(inputMultiplexer);
        EventManager.register(this);
        stage.draw();
    }

    @Override
    public void hide() {
        if (stage != null) {
            stage.dispose();
        }

        if (playButton != null) {
            playButton.dispose();
        }

        EventManager.unregister(this);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void handleEvent(Event event) {
        Gdx.app.log("Button", event.getName());
        if ("PlayButtonPressed".equals(event.getName())) {
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    game.setScreen(new GameScreen(game));
                }
            });
        }

        if(event.getName() == ScreenEvent.EXIT_GAME) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        playButton.dispose();
        exitButton.dispose();
    }
}
