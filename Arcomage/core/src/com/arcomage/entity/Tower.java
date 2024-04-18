package com.arcomage.entity;

import com.arcomage.core.Event;
import com.arcomage.core.EventListener;
import com.arcomage.core.EventManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Tower implements EventListener {
    private float x;
    private float y;
    private float w;
    private float h;

    public Tower(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void render(ShapeRenderer shapeRenderer) {
        if (h > 0) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.LIGHT_GRAY);
            shapeRenderer.rect(x, y, w, h);
            shapeRenderer.setColor(Color.FIREBRICK);
            shapeRenderer.triangle(x - 10, y + h, x + w + 10, y + h, x + w / 2, y + h + 50);
            shapeRenderer.end();
        }
    }

    @Override
    public void handleEvent(Event event) {
        if (event.getName().equals("CardSelected")) {
            if (h > 0) {
                h = h - 50;
            }
            if (h <= 0) {
                if (h < 0) h = 0;
                EventManager.publish(new Event("TowerDestroyed"));
            }
        }
    }

    public void dispose() {
    }
}
