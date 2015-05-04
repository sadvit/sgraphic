package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCurveEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class BezierCurveDrawer extends CurveDrawer {

    public BezierCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        int n = getPoints().size();
        double t = 0.01;
        Point2 prevp = getPoints().get(0);
        while (t <= 1) {
            double[] b = new double[n];
            for (int i = 0; i < n; i++) {
                b[i] = bernsteinPolynomial(i, n - 1, t);
            }
            double sumx = 0;
            double sumy = 0;

            for (int i = 0; i < n; i++) {
                double v = b[i];
                Point2 p = getPoints().get(i);
                sumx += v * p.getX();
                sumy += v * p.getY();
            }

            Point2 p = new Point2(sumx, sumy);
            new ModBrezenhamDrawer(prevp, p, getBrush()).draw(canvas);

            prevp = p;
            t += 0.01;
        }
        new ModBrezenhamDrawer(prevp, getPoints().get(getPoints().size() - 1), getBrush()).draw(canvas);
    }

    private double bernsteinPolynomial(int i, int n, double t) {
        return Cnk(n, i) * Math.pow(t, i) * Math.pow(1 - t,  n - i);
    }

    private int Cnk(int n, int k) {
        return factorial(n) / factorial(n - k) / factorial(k);
    }

    private int factorial(int n) {
        int r = 1;
        for (int i = 1; i <= n; i++) {
            r *= i;
        }
        return r;
    }

}
