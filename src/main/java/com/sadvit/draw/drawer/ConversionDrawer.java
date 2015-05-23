package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.event.DrawConversionEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;

import java.util.LinkedList;
import java.util.List;

public class ConversionDrawer implements Drawer {

    private DrawConversionEvent event;

    public ConversionDrawer(DrawConversionEvent event) {
        this.event = event;
    }

    private boolean checkPoint(Point2 p, SimpleCanvas canvas) {
        return p.getX() > 0 && p.getY() > 0 && p.getY() < canvas.getHeight() && p.getX() < canvas.getWidth();
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        canvas.clean();
        LinkedList<Point2> result = new LinkedList<>();
        LinkedList<Point2> points = event.getPoints();
        for (Point2 p : points) {
            RealVector vector = p.toUniform();
            RealVector value = event.getMatrix().multiply(MatrixUtils.createColumnRealMatrix(vector.toArray())).getColumnVector(0);
            //RealVector value = event.getMatrix().preMultiply(vector);
            Point2 res = vectorToPoint(value);
            //if (checkPoint(res, canvas))
                result.add(res);
        }
        drawChain(result, canvas);
        /*SimpleCanvas simpleCanvas = canvas.clone();
        canvas.forEach(point -> {
            RealVector vector = point.toUniform();
            RealVector value = event.getMatrix().preMultiply(vector);
            Point2 result = vectorToPoint(value);
            if (checkPoint(result, canvas))
                simpleCanvas.setColor(result, canvas.getColor(point));
        });
        canvas.clean();
        canvas.forEach(point -> {
            Color color = simpleCanvas.getColor(point);
            canvas.setColor(point, color);
        });*/
    }

    private void drawChain(List<Point2> points, SimpleCanvas canvas) {
        for (int i = 0; i < points.size() - 1; i++) {
            drawClick(points.get(i), Color.RED, canvas);
            drawLine(points.get(i), points.get(i + 1), canvas);
        }
    }

    private void drawLine(Point2 p1, Point2 p2, SimpleCanvas canvas) {
        drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), canvas);
    }

    private void drawLine(int x0, int y0, int x1, int y1, SimpleCanvas canvas) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(javafx.scene.paint.Color.BLACK), 1);
        LineDrawer lineDrawer = new ModBrezenhamDrawer(new Point2(x0, y0), new Point2(x1, y1), brush);
        lineDrawer.draw(canvas);
    }

    private void drawClick(Point2 point, Color color, SimpleCanvas canvas) {
        if (point.getX() > 2 && point.getY() > 2 && point.getX() < canvas.getWidth() - 2 && point.getY() < canvas.getHeight() - 2) {
            canvas.setColor(new Point2(point.getX(), point.getY()), color);
            canvas.setColor(new Point2(point.getX() - 1, point.getY()), color);
            canvas.setColor(new Point2(point.getX() - 2, point.getY()), color);
            canvas.setColor(new Point2(point.getX(), point.getY() + 1), color);
            canvas.setColor(new Point2(point.getX(), point.getY() + 2), color);
            canvas.setColor(new Point2(point.getX() + 1, point.getY()), color);
            canvas.setColor(new Point2(point.getX() + 2, point.getY()), color);
            canvas.setColor(new Point2(point.getX(), point.getY() - 1), color);
            canvas.setColor(new Point2(point.getX(), point.getY() - 2), color);
        }
    }

    private Point2 vectorToPoint(RealVector vector) {
        double[] array = vector.toArray();
        return new Point2(array[0], array[1]);
    }

}
