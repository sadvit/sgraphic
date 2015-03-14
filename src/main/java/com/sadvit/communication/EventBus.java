package com.sadvit.communication;

import java.util.HashMap;
import java.util.Map;

public class EventBus {

    //--------------------------------------------------------------------

    private static EventBus instance;

    public synchronized static EventBus getInstance() {
        if (instance == null) instance = new EventBus();
        return instance;
    }

    //--------------------------------------------------------------------

    private Map<Class<? extends Event>, Handler<? extends Event>> handlers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public synchronized <T extends Event> void fireEvent(T event) {
        Handler<T> handler = (Handler<T>) handlers.get(event.getClass());
        handler.handle(event);
    }

    public synchronized <T extends Event> void registerHandler(Class<T> eventType, Handler<T> eventHandler) {
        handlers.put(eventType, eventHandler);
    }

    public synchronized <T extends Event> void unregisterHandler(Class<T> eventType) {
        handlers.remove(eventType);
    }

}
