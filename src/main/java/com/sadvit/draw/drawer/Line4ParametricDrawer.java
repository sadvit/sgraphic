package com.sadvit.draw.drawer;

import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point;

public class Line4ParametricDrawer extends LineDrawer {

    public Line4ParametricDrawer(DrawLineEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        double dx = (x2 - x1) / n;
        double dy = (y2 - y1) / n;
        double xi = x1;
        double yi = y1;
        for (int i = 0; i < n; i++) {
            if (dx >= dy) {
                getBrush().touch(new Point(xi + dx, yi), canvas);
            } else {
                getBrush().touch(new Point(xi, yi + dy), canvas);
            }
            xi += dx;
            yi += dy;
            getBrush().touch(new Point(xi, yi), canvas);
        }
    }

}
