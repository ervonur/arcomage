package com.arcomage.entity;

import com.arcomage.core.Event;
import com.arcomage.core.EventListener;
import com.arcomage.core.EventManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Card implements EventListener {
    private Color fillColor;
    private Color borderColor;
    private float x;
    private float y;
    private float w;
    private float h;
    private Sound sound;
    private boolean highlight;
    private boolean selected;
    private float xTemp;
    private float yTemp;
    private boolean usable;

    public Card(Color color, float x, float y, float w, float h) {
        this.fillColor = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        sound = Gdx.audio.newSound(Gdx.files.internal("card.mp3"));
        usable = true;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(fillColor);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, w, h);
        shapeRenderer.end();
        if (highlight || selected) {
            if (highlight)
                borderColor = Color.YELLOW;
            if (selected)
                borderColor = Color.GREEN;
            shapeRenderer.setColor(borderColor);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(x - 1, y - 1, w + 1, h + 1);
            shapeRenderer.end();
        }
    }

    public void mouseMoved(int screenX, int screenY) {
        boolean fx = screenX >= x && screenX <= x + w;
        boolean fy = Gdx.graphics.getHeight() - screenY >= y && Gdx.graphics.getHeight() - screenY <= y + h;
        highlight = fx && fy && !selected;
    }

    public void touchUp(int screenX, int screenY, int pointer, int button) {
        boolean fx = screenX >= x && screenX <= x + w;
        boolean fy = screenY >= y && Gdx.graphics.getHeight() - screenY <= y + h;
        if (fx && fy) {
            if (button == Input.Buttons.LEFT) {
                if (!usable) {
                    return;
                }
                if (!selected) {
                    selected = true;
                    xTemp = x;
                    yTemp = y;
                    x = Gdx.graphics.getWidth() / 2 - w / 2;
                    y = Gdx.graphics.getHeight() / 2 - h / 2;
                    sound.play();
                    EventManager.publish(new Event("CardSelected"));
                }
            } else if (button == Input.Buttons.RIGHT) {
                if (selected) {
                    selected = false;
                    x = xTemp;
                    y = yTemp;
                    sound.play();
                }
            }
        }
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getName().equals("TowerDestroyed")) {
            usable = false;
        }
    }

    public void dispose() {
        sound.dispose();
    }
}
