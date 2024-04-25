package com.arcomage;

import com.arcomage.entity.Card;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter implements InputProcessor {
    private Card card1;
    private Card card2;
    private Card card3;

    @Override
    public void create() {
        final int CARD_WIDTH = 80;
        final int CARD_HEIGHT = 120;

        final int MID_X = Gdx.graphics.getWidth() / 2 - CARD_WIDTH / 2;

        card1 = new Card("card_red.png", MID_X - CARD_WIDTH - 25, 25, CARD_WIDTH, CARD_HEIGHT);
        card2 = new Card("card_green.png", MID_X, 25, CARD_WIDTH, CARD_HEIGHT);
        card3 = new Card("card_blue.png", MID_X + CARD_WIDTH + 25, 25, CARD_WIDTH, CARD_HEIGHT);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        card1.render();
        card2.render();
        card3.render();
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
        card1.touchDown(screenX, screenY, pointer, button);
        card2.touchDown(screenX, screenY, pointer, button);
        card3.touchDown(screenX, screenY, pointer, button);
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

    @Override
    public void dispose() {
        card1.dispose();
        card2.dispose();
        card3.dispose();
    }
}
