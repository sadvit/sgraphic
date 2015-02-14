package com.sadvit.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * Одна в программе. Ссылку на этот обьект имеют все классы.
 */
public class EventBus {

    //--------------------------------------------------------------------

    private static EventBus instance;

    public synchronized static EventBus getInstance() {
        if (instance == null) instance = new EventBus();
        return instance;
    }

    //--------------------------------------------------------------------

    private List<MHandler<? extends MEvent>> handlers = new ArrayList<>();

    @SuppressWarnings({"unchecked", "Convert2streamapi"})
    public synchronized void handleEvent(MEvent event) {
        for (MHandler handler: handlers) {
            if (isCorrectHandler(handler, event)) handler.handle(event);
        }
    }

    public synchronized void registerHandler(MHandler<? extends MEvent> handler) {
        handlers.add(handler);
    }

    public synchronized void unregisterHandler(MHandler<? extends MEvent> handler) {
        handlers.remove(handler);
    }

    private synchronized boolean isCorrectHandler(MHandler handler, MEvent event) {
        return handler.getEventType().equals(event.getClass());
    }

}
