package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCircleEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class CircleBrezenhamDrawer extends CircleDrawer {

    public CircleBrezenhamDrawer(DrawCircleEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        int x = 0, y = r, gap, delta = (2 - 2 * r);
        while (y >= 0)
        {
            getBrush().touch(new Point2(x0 + x, y0 + y), canvas);
            getBrush().touch(new Point2(x0 + x, y0 - y), canvas);
            getBrush().touch(new Point2(x0 - x, y0 - y), canvas);
            getBrush().touch(new Point2(x0 - x, y0 + y), canvas);
            gap = 2 * (delta + y) - 1;
            if (delta < 0 && gap <= 0)
            {
                x++;
                delta += 2 * x + 1;
                continue;
            }
            if (delta > 0 && gap > 0)
            {
                y--;
                delta -= 2 * y + 1;
                continue;
            }
            x++;
            delta += 2 * (x - y);
            y--;
        }
    }

}
