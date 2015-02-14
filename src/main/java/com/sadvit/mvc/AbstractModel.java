package com.sadvit.mvc;


import com.sadvit.communication.EventBus;
import com.sadvit.communication.MEvent;

public class AbstractModel {

    public void fireEvent(MEvent event) {
        EventBus.getInstance().handleEvent(event);
    }

    public EventBus getEventBus() {
        return EventBus.getInstance();
    }

}
