package com.sadvit.mediator;

public class DrawMosaicEvent implements MEvent {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DrawMosaicEvent(int number) {
        this.number = number;
    }
}