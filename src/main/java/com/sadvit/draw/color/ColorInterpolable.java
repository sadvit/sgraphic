package com.sadvit.draw.color;

import javafx.scene.paint.Color;

/**
 * Интерполируемый цвет
 */
public class ColorInterpolable implements PaintBucket {

    private Color colorStart;

    private Color colorEnd;

    private double dt;

    private double ti;

    public ColorInterpolable(Color colorStart, Color colorEnd, int length) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
        double t = 1.0 / (length - 1.0);
        System.out.println("t " + t);
        System.out.println("len " + length);
        dt = t;// / (double) length;
    }

    /**
     * Каждый новый вызов будет возвращать новый, измененный цвет
     */
    @Override
    public Color getNextColor() {
        ti += dt;
        return colorStart.interpolate(colorEnd, ti);
    }

}
