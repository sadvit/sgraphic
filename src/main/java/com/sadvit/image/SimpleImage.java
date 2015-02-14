package com.sadvit.image;

import javafx.scene.paint.Color;

/**
 * Basic image interface.
 */
public interface SimpleImage {

    public Color getColor(int x, int y);

    public void setColor(int x, int y, Color color);

    public int getWidth();

    public int getHeight();

}
