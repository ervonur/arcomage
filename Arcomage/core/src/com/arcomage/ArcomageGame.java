package com.arcomage;

import com.arcomage.screen.MainMenuScreen;

public class ArcomageGame extends Game {

    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }
}
