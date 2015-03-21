package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.event.DrawCircleEvent;

public abstract class CircleDrawer implements Drawer {

    private Brush brush;

    protected int x0;

    protected int y0;

    protected int  r;

    public CircleDrawer(DrawCircleEvent event) {
        x0 = event.getCoordinates().getX();
        y0 = event.getCoordinates().getY();
        r = event.getRadius();
        brush = BrushFactory.getBrush(event.getBrushType(), new ColorAdaptor(event.getColor()), event.getBrushSize());
    }

    protected Brush getBrush() {
        return brush;
    }
}
