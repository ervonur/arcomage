package com.arcomage;

import com.arcomage.core.Event;
import com.arcomage.core.EventManager;
import com.arcomage.core.ScreenEvent;
import com.arcomage.entity.Card;
import com.arcomage.entity.Info;
import com.arcomage.entity.Tower;
import com.arcomage.screen.MainMenuScreen;
import com.arcomage.screen.PauseMenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter implements InputProcessor {
    private Screen currentScreen;


    @Override
    public void create() {
        Gdx.input.setInputProcessor(this);
    }

    public void setScreen(Screen screen) {
        if (currentScreen != null) currentScreen.hide();
        currentScreen = screen;
        if (currentScreen != null) currentScreen.show();
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
