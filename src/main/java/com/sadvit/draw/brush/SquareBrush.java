package com.sadvit.draw.brush;

import com.sadvit.draw.color.PaintBucket;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point;
import javafx.scene.paint.Color;

public class SquareBrush extends AbstractBrush {

    @Override
    public void touch(Point point, SimpleCanvas canvas) {
        Color color = getNextColor();
        int size = getSize();
        int x = point.getX();
        int y = point.getY();
        for (int i = -size; i < size; i++) {
            for (int j = -size; j < size; j++) {
                canvas.setColor(x + i, y + j, color);
            }
        }
    }

    public SquareBrush(PaintBucket paintBucket, int size) {
        super(paintBucket, size);
    }

}
