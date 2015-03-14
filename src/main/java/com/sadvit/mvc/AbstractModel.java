package com.sadvit.mvc;


import com.sadvit.communication.Event;
import com.sadvit.communication.EventBus;

public class AbstractModel {

    public void fireEvent(Event event) {
        EventBus.getInstance().fireEvent(event);
    }

    public EventBus getEventBus() {
        return EventBus.getInstance();
    }

}
