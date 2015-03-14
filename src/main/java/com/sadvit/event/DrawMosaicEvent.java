package com.sadvit.event;

import com.sadvit.communication.Event;

public class DrawMosaicEvent implements Event {

    private int number;

    public int getSize() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DrawMosaicEvent(int number) {
        this.number = number;
    }
}