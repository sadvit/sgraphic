package com.sadvit.draw.drawer;

import com.sadvit.draw.BoundType;
import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.draw.color.ColorInterpolable;
import com.sadvit.draw.color.PaintBucket;
import com.sadvit.draw.template.DashDotTemplate;
import com.sadvit.draw.template.DashTemplate;
import com.sadvit.event.DrawLineEvent;

public abstract class LineDrawer implements Drawer {

    private Brush brush;

    protected int x1;

    protected int x2;

    protected int y1;

    protected int y2;

    protected double n;

    public LineDrawer(DrawLineEvent event) {
        x1 = event.getP1().getX();
        y1 = event.getP1().getY();
        x2 = event.getP2().getX();
        y2 = event.getP2().getY();
        n = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        PaintBucket paintBucket;
        if (event.getColorStart().equals(event.getColorEnd())) {
            paintBucket = new ColorAdaptor(event.getColorStart());
        } else {
            int k = event.getBoundType() == BoundType.X8 ? 1 : 2;
            int lenght = (event.getP1().length(event.getP2())) * k; // / (event.getBrushSize() * (1.5 - k)))
            paintBucket = new ColorInterpolable(event.getColorStart(), event.getColorEnd(), lenght);
        }
        brush = BrushFactory.getBrush(event.getBrushType(), paintBucket, event.getBrushSize());
        switch (event.getTrafaretType()) {
            case SOLID:
                break;
            case DASH_DOTTED:
                brush = new DashDotTemplate(brush);
                break;
            case DASHED:
                brush = new DashTemplate(brush);
                break;
        }
    }

    protected Brush getBrush() {
        return brush;
    }

}