package com.sadvit.draw.brush;

import com.sadvit.draw.color.PaintBucket;
import javafx.scene.paint.Color;

public abstract class AbstractBrush implements Brush {

    private int size;

    private PaintBucket paintBucket;

    protected Color getNextColor() {
        return paintBucket.getNextColor();
    }

    @Override
    public int getSize() {
        return size;
    }

    public AbstractBrush(PaintBucket paintBucket, int size) {
        this.size = size;
        this.paintBucket = paintBucket;
    }

}