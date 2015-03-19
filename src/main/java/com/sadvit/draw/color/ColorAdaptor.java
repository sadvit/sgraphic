package com.sadvit.draw.color;

import javafx.scene.paint.Color;

public class ColorAdaptor implements PaintBucket {

    private Color color;

    @Override
    public Color getNextColor() {
        return color;
    }

    public ColorAdaptor(Color color) {
        this.color = color;
    }

}
