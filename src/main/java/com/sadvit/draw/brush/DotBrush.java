package com.sadvit.draw.brush;

import com.sadvit.draw.color.PaintBucket;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point;

public class DotBrush extends AbstractBrush {

    @Override
    public void touch(Point point, SimpleCanvas canvas) {
        canvas.setColor(point, getNextColor());
    }

    public DotBrush(PaintBucket paintBucket, int size) {
        super(paintBucket, size);
    }

}
