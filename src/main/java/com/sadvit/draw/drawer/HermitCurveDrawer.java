package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCurveEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.HermitPoint;
import com.sadvit.math.HermitVector;
import com.sadvit.math.Point2;

import java.util.Vector;
import java.util.stream.Collectors;

public class HermitCurveDrawer extends CurveDrawer {

    public static final double TENSION = 0.1;
    public static final double STEP = 0.001;

    private Vector<HermitPoint> points = new Vector<>();

    public HermitPoint polynom(HermitPoint point1, HermitPoint point2, double t) {
        double f1 = 2 * t * t * t - 3 * t * t + 1;
        double f2 = -2 * t * t * t + 3 * t * t;
        double f3 = t * t * t - 2 * t * t + t;
        double f4 = t * t * t - t * t;
        HermitPoint point = new HermitPoint();
        point.addHermitPoint(f1, point1);
        point.addHermitPoint(f2, point2);
        point.addHermitVector(f3, point1.getDirection());
        point.addHermitVector(f4, point2.getDirection());
        return point;
    }

    public HermitVector direction(HermitPoint point1, HermitPoint point2, double c) {
        HermitVector v = new HermitVector(0, 0);
        v.setX(0.5 * (1 - c) * (point2.getX() - point1.getX()));
        v.setY(0.5 * (1 - c) * (point2.getY() - point1.getY()));
        return v;
    }

    public void setDirection(double c) {
        HermitPoint point = points.elementAt(0);
        point.setDirection(direction(points.elementAt(0), points.elementAt(1), c));

        point = points.elementAt(points.size() - 1);
        point.setDirection(direction(points.elementAt(points.size() - 2), points.elementAt(0), c));

        for (int i = 1; i < (points.size() - 1); i++) {
            point = points.elementAt(i);
            point.setDirection(direction(points.elementAt(i - 1), points.elementAt(i + 1), c));
        }
    }

    public void draw(SimpleCanvas canvas) {

        points.addAll(getPoints().stream()
                .map(p -> new HermitPoint(p.getX(), p.getY()))
                .collect(Collectors.toList()));

        if (points.size() < 2) {
            return;
        }

        HermitPoint point0 = points.elementAt(0);
        HermitPoint point1 = points.elementAt(1);

        int x1 = (int) (point0.getX());
        int yn1 = (int) (- point0.getY());
        int x0 = (int) (point1.getX() );
        int yn0 = (int) (- point1.getY());

        if (points.size() == 2) {
            new ModBrezenhamDrawer(new Point2(x1, yn1), new Point2(x0, yn0), getBrush()).draw(canvas);
            return;
        }

        setDirection(TENSION);

        for (int i = 0; i < (points.size()); i++) {
            point0 = points.elementAt(i);
            if (i == points.size() - 1) {
                break;
            } else {
                point1 = points.elementAt(i + 1);
            }

            x1 = (int) (point0.getX());
            yn1 = (int) (- point0.getY());

            for (double t = 0; t < 1; t = t + STEP) {

                HermitPoint point = polynom(point0, point1, t);

                x0 = (int) (point.getX());
                yn0 = (int) (- point.getY());

                new ModBrezenhamDrawer(new Point2(x1, yn1), new Point2(x0, yn0), getBrush()).draw(canvas);
                x1 = x0;
                yn1 = yn0;

            }
        }
    }

    public HermitCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

}