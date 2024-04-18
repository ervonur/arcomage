package com.arcomage.component.menu;

import com.arcomage.core.Event;
import com.arcomage.core.EventListener;
import com.arcomage.core.EventManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.arcomage.entity.Button;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Menu,EventListener {
    private List<Button> buttons;
    private ShapeRenderer shapeRenderer;

    public MainMenu(ShapeRenderer shapeRenderer){
        this.shapeRenderer = shapeRenderer;
        buttons = new ArrayList<>();

        float buttonWidth = 150;
        float buttonHeight = 50;
        float centerX = Gdx.graphics.getWidth()/2-buttonWidth/2;
        float startY = Gdx.graphics.getHeight()/2-buttonHeight/2;

        buttons.add(new Button("Start",centerX,startY + buttonHeight*2,buttonWidth,buttonHeight,Color.GREEN));
        buttons.add(new Button("Options",centerX,startY + buttonWidth*2,buttonHeight,buttonHeight,Color.BLUE));
        buttons.add(new Button("Exit",centerX,startY - buttonHeight*2,buttonWidth,buttonHeight,Color.RED));

        for(Button button : buttons){
            EventManager.register((EventListener)button);
        }
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(Button button: buttons){
            button.render(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public void touchUp(int screenX, int screenY, int pointer, int button){
        for(Button btn: buttons){
            btn.touchUp(screenX,screenY,pointer,button);
        }
    }

    public void dispose(){
        for(Button btn: buttons){
            btn.dispose();
        }
    }

    @Override
    public void handleEvent(Event event) {
        if("ButtonPressed:Start".equals(event.getName())){
            
        } else if ("ButtonPressed:Start".equals(event.getName())) {
            
        } else if ("ButtonPressed:Exit".equals(event.getName())) {
            Gdx.app.exit();
        }
    }
}
