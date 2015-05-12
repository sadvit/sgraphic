package com.sadvit.communication;

import java.util.HashMap;
import java.util.Map;

public class EventBus {

    //--------------------------------------------------------------------

    private static EventBus instance;

    public static EventBus getInstance() {
        if (instance == null) instance = new EventBus();
        return instance;
    }

    //--------------------------------------------------------------------

    private Map<Class<? extends Event>, Handler<? extends Event>> handlers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends Event> void fireEvent(T event) {
        Handler<T> handler = (Handler<T>) handlers.get(event.getClass());
        if (handler == null) {
            System.err.println("Handler is not register to event: " + event.getClass().getSimpleName());
        } else {
            handler.handle(event);
        }
    }

    public <T extends Event> void registerHandler(Class<T> eventType, Handler<T> eventHandler) {
        handlers.put(eventType, eventHandler);
    }

    public <T extends Event> void unregisterHandler(Class<T> eventType) {
        handlers.remove(eventType);
    }

}
