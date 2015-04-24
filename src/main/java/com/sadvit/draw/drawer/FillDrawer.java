package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.draw.color.PaintBucket;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

import java.util.List;

public abstract class FillDrawer implements Drawer {

    private List<Point2> points;

    public FillDrawer(List<Point2> points) {
        Point2 point = points.get(0);
        points.add(point);
        this.points = points;
    }

    protected Color colorOutline = Color.BLACK;

    protected List<Point2> getPoints() {
        return points;
    }

    protected void drawChain(SimpleCanvas canvas) {
        PaintBucket paintBucket = new ColorAdaptor(Color.BLACK);
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, paintBucket, 1);
        Point2 prev = getPoints().get(0);
        for (int i = 1; i < getPoints().size(); i++) {
            Point2 next = getPoints().get(i);
            new Line8ParametricDrawer(prev, next, brush).draw(canvas);
            prev = next;
        }
    }

}
