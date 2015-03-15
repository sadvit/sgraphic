package com.sadvit.draw;

import com.sadvit.image.SimpleImage;
import com.sadvit.math.Point;
import javafx.scene.paint.Color;

public class Brush {

    public static void circle(SimpleImage image, Point point, Color color, int size) {

    }

    // TODO add size
    public static void square(SimpleImage image, Point point, Color color) {
        int x = point.getX();
        int y = point.getY();
        image.setColor(x, y, color);
        image.setColor(x + 1, y, color);
        image.setColor(x - 1, y, color);
        image.setColor(x, y + 1, color);
        image.setColor(x, y - 1, color);
        image.setColor(x + 1, y + 1, color);
        image.setColor(x - 1, y - 1, color);
        image.setColor(x + 1, y - 1, color);
        image.setColor(x - 1, y + 1, color);
    }

    public static void draw(BrushType type, SimpleImage image, Point point, Color color) {
        int size = type.getSize() > 0 ? type.getSize() : 5;
        // TODO realize
    }

}
