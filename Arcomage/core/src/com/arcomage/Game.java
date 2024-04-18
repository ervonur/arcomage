package com.arcomage;

import com.arcomage.entity.Card;
import com.arcomage.entity.Info;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter implements InputProcessor {
    private ShapeRenderer shapeRenderer;
    private Info info1;
    private Info info2;
    private Card card;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        float w = 100;
        float h = 125;
        float x = Gdx.graphics.getWidth() / 2 - w / 2;
        float y = 25;
        card = new Card(Color.RED, x, y, w, h);
        info1 = new Info("* Left click on the card to select it.", Color.WHITE, 10, Gdx.graphics.getHeight() - 10);
        info2 = new Info("* Right click on the card to deselect it.", Color.WHITE, 10, Gdx.graphics.getHeight() - 30);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        info1.render();
        info2.render();
        card.render(shapeRenderer);
        card.render(shapeRenderer);
        card.render(shapeRenderer);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        info1.dispose();
        info2.dispose();
        card.dispose();
        card.dispose();
        card.dispose();
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
        card.touchUp(screenX, screenY, pointer, button);
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
        card.mouseMoved(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
