package com.arcomage.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Card {
    private float x;
    private float y;

    private float width;
    private float height;

    private Texture texture;
    private Image image;
    private SpriteBatch spriteBatch;

    private Action action1;
    private Action action2;
    private Action actionComplete;
    private int actionType;

    private Sound sound;

    private static boolean available = true;

    public Card(String fileName, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.texture = new Texture(Gdx.files.internal(fileName));

        this.action1 = getAction1();
        this.action2 = getAction2();
        this.actionComplete = getActionComplete();

        this.image = new Image(texture);
        this.image.setPosition(x, y);
        this.image.addAction(Actions.sequence(action1, actionComplete));

        this.spriteBatch = new SpriteBatch();

        this.sound = Gdx.audio.newSound(Gdx.files.internal("card.mp3"));
    }

    private Action getAction1() {
        return Actions.moveTo(
            Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2,
            0.30f
        );
    }

    private Action getAction2() {
        return Actions.moveTo(
            50,
            Gdx.graphics.getHeight() - height - 50,
            0.30f
        );
    }

    private Action getActionComplete() {
        return new Action() {
            @Override
            public boolean act(float delta) {
                switch (actionType) {
                    case 1:
                        actionType = 2;
                        image.getActions().clear();
                        image.addAction(Actions.sequence(action2, actionComplete));
                        sound.play();
                        break;
                    case 2:
                        actionType = 0;
                        image.getActions().clear();
                        available = true;
                        break;
                }
                return true;
            }
        };
    }

    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(texture, image.getX(), image.getY());
        spriteBatch.end();

        if (actionType == 1 || actionType == 2) {
            image.act(Gdx.graphics.getDeltaTime());
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button) {
        if (!available)
            return;

        screenY = Gdx.graphics.getHeight() - screenY;

        boolean collideX = screenX >= x && screenX <= (x + width);
        boolean collideY = screenY >= y && screenY <= (y + height);

        if (collideX && collideY && actionType == 0) {
            actionType = 1;
            available = false;
            sound.play();
        }
    }

    public void mouseMoved(int screenX, int screenY) {
    }

    public void dispose() {
        texture.dispose();
        spriteBatch.dispose();
        sound.dispose();
    }
}
