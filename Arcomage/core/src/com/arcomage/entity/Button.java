package com.arcomage.entity;

import com.arcomage.core.EventManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;

public class Button implements EventListener {
    private Rectangle bounds;
    private String label;
    private Color color;
    private boolean isPressed = false;
    private BitmapFont font;
    private SpriteBatch spriteBatch;

    public Button(String label, float x, float y, float width, float height, Color color){
        this.label = label;
        this.color = color;
        bounds = new Rectangle(x, y, width, height);

        font = new BitmapFont();
        spriteBatch = new SpriteBatch();
    }

    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(bounds.x,bounds.y,bounds.width,bounds.height);
        shapeRenderer.end();

        spriteBatch.begin();
        font.setColor(Color.WHITE);
        font.draw(spriteBatch,label,bounds.x+(bounds.width/2) - (font.getRegion().getRegionWidth()/2),
                bounds.y+(bounds.height/2)+(font.getRegion().getRegionHeight()/2));
        spriteBatch.end();
    }

    @Override
    public void handleEvent(Event evt) {

    }

    public boolean isMouseOver(int screenX, int screenY){
        int libGDXScreenY = Gdx.graphics.getHeight() - screenY;
        return  bounds.contains(screenX,libGDXScreenY);
    }

    public  void touchUp(int screenX, int screenY, int pointer, int button){
        if(isMouseOver(screenX,screenY)){
            if(button == Input.Buttons.LEFT){
                isPressed = false;
                EventManager.publish(new com.arcomage.core.Event("Button pressed:"+label));
            }
        }
    }

    public void touchDown(int screenX, int screenY, int pointer, int button){
        if(isMouseOver(screenX,screenY)){
            if (button == Input.Buttons.LEFT){
                isPressed = true;
            }
        }
    }

    public void dispose(){
        font.dispose();
        spriteBatch.dispose();
    }
}
