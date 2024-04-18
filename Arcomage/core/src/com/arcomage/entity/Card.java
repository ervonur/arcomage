package com.arcomage.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Card {
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

    public Card(Color color, float x, float y, float w, float h) {
        this.fillColor = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        sound = Gdx.audio.newSound(Gdx.files.internal("card.mp3"));
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
                if (!selected) {
                    selected = true;
                    xTemp = x;
                    yTemp = y;
                    x = Gdx.graphics.getWidth() / 2 - w / 2;
                    y = Gdx.graphics.getHeight() / 2 - h / 2;
                    sound.play();
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

    public void dispose() {
        sound.dispose();
    }
}
