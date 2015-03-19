package com.sadvit.image;

import com.sadvit.math.Point;
import javafx.scene.paint.Color;

/**
 * Basic image interface.
 */
public interface SimpleCanvas {

    public Color getColor(int x, int y);

    public void setColor(int x, int y, Color color);

    public Color getColor(Point point);

    public void setColor(Point point, Color color);

    public int getWidth();

    public int getHeight();

}
