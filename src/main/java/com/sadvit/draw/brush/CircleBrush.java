package com.sadvit.draw.brush;

import com.sadvit.draw.color.PaintBucket;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

public class CircleBrush extends AbstractBrush {

    private Color current;

    public CircleBrush(PaintBucket paintBucket, int size) {
        super(paintBucket, size);
    }

    private void fill(int y, int x0, int xn, SimpleCanvas canvas) {
        for(int x = x0; x < xn; x++) {
            canvas.setColor(x, y, current);
        }
    }

    @Override
    public void touch(Point2 point, SimpleCanvas canvas) {
        int r = getSize();
        int x0 = point.getX();
        int y0 = point.getY();
        current = getNextColor();
        for (int x = 0; x < r/Math.sqrt(2); x++) {
            int y = (int) Math.sqrt(r * r - x * x);
            canvas.setColor(x0 + x, y0 + y, Color.RED);
            canvas.setColor(x0 + y, y0 + x, Color.RED);
            canvas.setColor(x0 + y, y0 - x, Color.RED);
            canvas.setColor(x0 + x, y0 - y, Color.RED);
            canvas.setColor(x0 - x, y0 - y, Color.RED);
            canvas.setColor(x0 - y, y0 - x, Color.RED);
            canvas.setColor(x0 - y, y0 + x, Color.RED);
            canvas.setColor(x0 - x, y0 + y, Color.RED);
            fill(y0 + y, x0 - x, x0 + x, canvas);
            fill(y0 - y, x0 - x, x0 + x, canvas);
            fill(y0 - x, x0 - y, x0 + y, canvas);
            fill(y0 + x, x0 - y, x0 + y, canvas);
        }
    }
}
