package com.arcomage.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Info {
    private float x;
    private float y;
    private String text;
    private SpriteBatch spriteBatch = new SpriteBatch();
    private BitmapFont bitmapFont = new BitmapFont();

    public Info(String text, Color color, float x, float y) {
        this.text = text;
        this.bitmapFont.setColor(color);
        this.x = x;
        this.y = y;
    }

    public void render() {
        spriteBatch.begin();
        bitmapFont.draw(spriteBatch, text, x, y);
        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        bitmapFont.dispose();
    }
}
