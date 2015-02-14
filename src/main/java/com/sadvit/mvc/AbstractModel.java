package com.sadvit.mvc;


import com.sadvit.mediator.EventBus;
import com.sadvit.mediator.MEvent;

public class AbstractModel {

    private EventBus eventBus;

    public void fireEvent(MEvent event) {
        eventBus.handleEvent(event);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

}
