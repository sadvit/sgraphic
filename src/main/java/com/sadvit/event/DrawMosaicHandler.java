package com.sadvit.event;

import com.sadvit.communication.MHandler;

public abstract class DrawMosaicHandler implements MHandler<DrawMosaicEvent> {

    @Override
    public Class<DrawMosaicEvent> getEventType() {
        return DrawMosaicEvent.class;
    }

}