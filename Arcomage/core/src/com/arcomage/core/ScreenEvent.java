package com.arcomage.core;

public class ScreenEvent extends Event {
    public static final String PLAY_GAME = "PlayGame";
    public static final String PAUSE_GAME = "PauseGame";
    public static final String RESUME_GAME = "ResumeGame";
    public static final String EXIT_GAME = "ExitGame";

    public ScreenEvent(String name) {
        super(name);
    }
}
