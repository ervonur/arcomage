package com.arcomage.core;

import java.util.ArrayList;
import java.util.List;

public final class EventManager {
    private static List<EventListener> listeners = new ArrayList<>();

    private EventManager() {
    }

    public static void register(EventListener eventListener) {
        listeners.add(eventListener);
    }

    public static void publish(Event event) {
        for (EventListener listener : listeners) {
            listener.handleEvent(event);
        }
    }
}
