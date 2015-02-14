package com.sadvit.event;

import com.sadvit.communication.MEvent;

public class DrawMosaicEvent implements MEvent {

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