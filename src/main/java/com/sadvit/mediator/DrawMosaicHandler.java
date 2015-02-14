package com.sadvit.mediator;

public abstract class DrawMosaicHandler implements MHandler<DrawMosaicEvent> {

    @Override
    public Class<DrawMosaicEvent> getEventType() {
        return DrawMosaicEvent.class;
    }

}