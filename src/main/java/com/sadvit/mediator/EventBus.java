package com.sadvit.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Одна в программе. Ссылку на этот обьект имеют все классы.
 */
// зарегестрирован в контексте спринга, синглтон
public abstract class EventBus {

    private List<MHandler<? extends MEvent>> handlers = new ArrayList<>();

    @SuppressWarnings({"unchecked", "Convert2streamapi"})
    public void handleEvent(MEvent event) {
        for (MHandler handler: handlers) {
            if (isCorrectHandler(handler, event)) handler.handle(event);
        }
    }

    public void registerHandler(MHandler<? extends MEvent> handler) {
        handlers.add(handler);
    }

    public void unregisterHandler(MHandler<? extends MEvent> handler) {
        handlers.remove(handler);
    }

    private boolean isCorrectHandler(MHandler handler, MEvent event) {
        return handler.getEventType().equals(event.getClass());
    }

}
