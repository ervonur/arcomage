package com.arcomage.screen;


import com.arcomage.ArcomageGame;
import com.arcomage.Game;
import com.arcomage.core.EventManager;
import com.arcomage.entity.Card;
import com.arcomage.entity.Info;
import com.arcomage.entity.Tower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends AbstractScreen implements InputProcessor {
    public GameScreen(Game game) {
        super(game);
    }

    private ShapeRenderer shapeRenderer;
    private Info info1, info2;
    private Card card;
    private Tower tower;

    public GameScreen(ArcomageGame game) {
        super(game);
        shapeRenderer = new ShapeRenderer();
        createInfo();
        createCard();
        createTower();
        Gdx.input.setInputProcessor(this);
        EventManager.register(card);
        EventManager.register(tower);
    }

    private void createCard() {
        float w = 70;
        float h = 100;
        float x = Gdx.graphics.getWidth() / 2 - w / 2;
        float y = 25;
        card = new Card(Color.RED, x, y, w, h);
    }

    private void createInfo() {
        info1 = new Info("* Left click on the card to select it.", Color.WHITE, 10, Gdx.graphics.getHeight() - 10);
        info2 = new Info("* Right click on the card to deselect it.", Color.WHITE, 10, Gdx.graphics.getHeight() - 30);
    }

    private void createTower() {
        float x = 25;
        float y = 25;
        float w = 50;
        float h = 300;
        tower = new Tower(x, y, w, h);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        info1.render();
        info2.render();
        card.render(shapeRenderer);
        tower.render(shapeRenderer);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        info1.dispose();
        info2.dispose();
        card.dispose();
        tower.dispose();
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
