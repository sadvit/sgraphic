package com.sadvit.draw.drawer;

import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

@SuppressWarnings("ConstantConditions")
public class Line8BrezenhamDrawer extends LineDrawer {

    public Line8BrezenhamDrawer(DrawLineEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        int w = x2 - x1;
        int h = y2 - y1;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w < 0) dx1 = -1;
        else if (w > 0) dx1 = 1;
        if (h < 0) dy1 = -1;
        else if (h > 0) dy1 = 1;
        if (w < 0) dx2 = -1;
        else if (w > 0) dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        if (!(longest > shortest)) {
            longest = Math.abs(h);
            shortest = Math.abs(w);
            if (h < 0) dy2 = -1;
            else if (h > 0) dy2 = 1;
            dx2 = 0;
        }
        int numerator = longest >> 1;
        for (int i = 0; i <= longest; i++) {
            getBrush().touch(new Point2(x1, y1), canvas);
            numerator += shortest;
            if (!(numerator < longest)) {
                numerator -= longest;
                x1 += dx1;
                y1 += dy1;
            } else {
                x1 += dx2;
                y1 += dy2;
            }
        }
    }

}
