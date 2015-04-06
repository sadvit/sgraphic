package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class Line8ParametricDrawer extends LineDrawer {

    public Line8ParametricDrawer(DrawLineEvent event) {
        super(event);
    }

    public Line8ParametricDrawer(Point2 point1, Point2 point2, Brush brush) {
        super(point1, point2, brush);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        double dx = (x2 - x1) / n;
        double dy = (y2 - y1) / n;
        double xi = x1;
        double yi = y1;
        for (int i = 0; i < n; i++) {
            xi += dx;
            yi += dy;
            getBrush().touch(new Point2((int)xi, (int)yi), canvas);
        }
    }

}
