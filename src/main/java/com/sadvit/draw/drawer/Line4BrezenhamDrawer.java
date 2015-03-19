package com.sadvit.draw.drawer;

import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point;

public class Line4BrezenhamDrawer extends LineDrawer {

    public Line4BrezenhamDrawer(DrawLineEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        int x = x1, y = y1;
        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1);
        int sx = (x2 - x1) > 0 ? 1 : ((x2 - x1) == 0 ? 0 : -1);
        int sy = (y2 - y1) > 0 ? 1 : ((y2 - y1) == 0 ? 0 : -1);
        int e = 2 * dy - dx;
        boolean change = false;
        if (dy > dx) {
            int z = dx;
            dx = dy;
            dy = z;
            change = true;
        }
        for (int k = 1; k <= (dx + dy); k++) {
            if (e < dx) {
                if (change) y += sy;
                else x += sx;
                e += 2 * dy;
            } else {
                if (change) x += sx;
                else y = y + sy;
                e -= 2 * dx;
            }
            getBrush().touch(new Point(x, y), canvas);
        }
    }
}
