package com.sadvit.image;

import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

/**
 * Basic image interface.
 */
public interface SimpleCanvas {

    public Color getColor(int x, int y);

    public void setColor(int x, int y, Color color);

    public Color getColor(Point2 point);

    public void setColor(Point2 point, Color color);

    public int getWidth();

    public int getHeight();

    public void clean();

}
