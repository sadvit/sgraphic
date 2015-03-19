package com.sadvit.draw.brush;

public enum BrushType {

    SQUARE, CIRCLE;

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    BrushType() {

    }

    BrushType(int size) {
        this.size = size;
    }

}
