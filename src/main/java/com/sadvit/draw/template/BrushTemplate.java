package com.sadvit.draw.template;

import com.sadvit.draw.brush.Brush;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

/**
 * Специальный обьект - декоратор, который
 * либо позволяет кисти оставить отметину на бумаге, либо нет.
 */
public abstract class BrushTemplate implements Brush {

    private Brush brush;

    protected Brush getBrush() {
        return brush;
    }

    @Override
    public int getSize() {
        return brush.getSize();
    }

    @Override
    public void touch(Point2 point, SimpleCanvas canvas) {
        if (isNextEnabled())
            brush.touch(point, canvas);
    }

    /**
     * Метод определяет, будет ли следующее касание кисти оставлять след на бумаге
     */
    protected abstract boolean isNextEnabled();

    public BrushTemplate(Brush brush) {
        this.brush = brush;
    }

}
