package com.arcomage.entity;

import com.arcomage.core.EventManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class Button implements EventListener, InputProcessor {
    private Rectangle bounds;
    private String label;
    private Color color;
    private boolean isPressed = false;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private GlyphLayout layout;

    public Button(String label, float x, float y, float width, float height, Color color){
        this.label = label;
        this.color = color;
        bounds = new Rectangle(x, y, width, height);
        layout = new GlyphLayout();

        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        createButton();
    }

    private void createButton(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(isPressed ? Color.DARK_GRAY : color);
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
        shapeRenderer.end();

        spriteBatch.begin();
        font.setColor(Color.RED);


        layout.setText(font, label);
        float textX = bounds.x + (bounds.width - layout.width) / 2;
        float textY = bounds.y + (bounds.height + layout.height) / 2;
        font.draw(spriteBatch, label, textX, textY);
        spriteBatch.end();
        Gdx.app.log("Button", "Button ıs drawn");
    }

    public void render(){
        Gdx.app.log("Button", "Button ıs rendering.");
    }

    @Override
    public void handleEvent(Event evt) {

    }

    public void update() {
        if (Gdx.input.justTouched()) {
            if (isMouseOver(Gdx.input.getX(), Gdx.input.getY())) {
                EventManager.publish(new com.arcomage.core.Event("Button pressed: " + label));
                Gdx.app.log("Button", "Button ıs pressed in update");
            }
        }
    }

    public boolean isMouseOver(int screenX, int screenY){
        int libGDXScreenY = Gdx.graphics.getHeight() - screenY;
        return  bounds.contains(screenX,libGDXScreenY);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button){
        if (isPressed && isMouseOver(screenX, screenY)) {
            EventManager.publish(new com.arcomage.core.Event(this.label+"ButtonPressed"));
        }
        isPressed = false;
        return true;
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        if (button == Input.Buttons.LEFT && isMouseOver(screenX, screenY)) {
            isPressed = true;
            return true;
        }
        return false;
    }

    public void dispose(){
        font.dispose();
        spriteBatch.dispose();
    }
}
