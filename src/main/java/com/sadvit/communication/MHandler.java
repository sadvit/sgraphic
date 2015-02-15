package com.sadvit.communication;

public interface MHandler<E extends MEvent> {

    public void handle(E event);

    public Class<E> getEventType();

}