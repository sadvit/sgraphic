package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCircleEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class CircleParametricDrawer extends CircleDrawer {

    public CircleParametricDrawer(DrawCircleEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        for (int x = 0; x < r/Math.sqrt(2); x++) {
            int y = (int) Math.round(Math.sqrt(r * r - x * x));
            getBrush().touch(new Point2(x0 + x, y0 + y), canvas);
            getBrush().touch(new Point2(x0 + y, y0 + x), canvas);
            getBrush().touch(new Point2(x0 + y, y0 - x), canvas);
            getBrush().touch(new Point2(x0 + x, y0 - y), canvas);
            getBrush().touch(new Point2(x0 - x, y0 - y), canvas);
            getBrush().touch(new Point2(x0 - y, y0 - x), canvas);
            getBrush().touch(new Point2(x0 - y, y0 + x), canvas);
            getBrush().touch(new Point2(x0 - x, y0 + y), canvas);
        }
    }

}
