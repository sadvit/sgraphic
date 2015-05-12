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
import com.sadvit.math.Point2;

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

    public LineDrawer(Point2 point1, Point2 point2, Brush brush) {
        this.brush = brush;
        x1 = point1.getX();
        y1 = point1.getY();
        x2 = point2.getX();
        y2 = point2.getY();
        n = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

    protected Brush getBrush() {
        return brush;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void recalc() {
        n = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

}