package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCurveEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class BezierCurveDrawer extends CurveDrawer {

    private static float BEZIER_MATRIX[][] = {
            {-1,  3, -3,  1},
            { 3, -6,  3,  0},
            {-3,  3,  0,  0},
            { 1,  0,  0,  0},
    };

    public BezierCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        int np = getPoints().size();
        float geom[][] = new float[4][2];
        float mg[][] = new float[4][2];
        float plot[][] = new float[4][2];

        for (int i = 0; i < np - 3; i += 3) {

            for (int j = 0; j < 4; j++) {
                geom[j][0] = getPoints().get(i + j).getX();
                geom[j][1] = getPoints().get(i + j).getY();
            }
            multMatrix(BEZIER_MATRIX, geom, mg);
            multMatrix(eMatrix, mg, plot);
            float startX = plot[0][0];
            float x = startX;
            float startY = plot[0][1];
            float y = startY;

            for (int j = 0; j < PRECISION; j++) {
                x += plot[1][0];
                plot[1][0] += plot[2][0];
                plot[2][0] += plot[3][0];
                y += plot[1][1];
                plot[1][1] += plot[2][1];
                plot[2][1] += plot[3][1];

                new Line8ParametricDrawer(new Point2(startX, startY), new Point2(x, y), getBrush()).draw(canvas);

                startX = x;
                startY = y;
            }
        }
    }

    /*public BezierCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        double dt = 1.0 / 1000.0;
        Point2 prev = getBezierPoint(dt);
        for (double t = 2 * dt; t <= 1; t += dt) {
            Point2 next = getBezierPoint(t);
            new Line4ParametricDrawer(prev, next, getBrush()).draw(canvas);
            prev = next;
        }
    }

    private Point2 getBezierPoint(double t) {
        int m = getPoints().size();
        Point2[] R = new Point2[m];
        for (int i = 0; i < m; i++) {
            R[i] = new Point2(getPoints().get(i).getX(), getPoints().get(i).getY());
        }
        for (int j = m; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                R[i].setX((int) (R[i].getX() + t * (R[i + 1].getX() - R[i].getX())));
                R[i].setY((int) (R[i].getY() + t * (R[i + 1].getY() - R[i].getY())));
            }
        }
        return new Point2(R[0].getX(), R[0].getY());
    }*/

}
