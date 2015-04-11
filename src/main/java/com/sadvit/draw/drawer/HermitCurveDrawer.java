package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCurveEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class HermitCurveDrawer extends CurveDrawer {

    private static final float HERMITE_MATRIX[][] = {
            { 2, -2,  1,  1},
            {-3,  3, -2, -1},
            { 0,  0,  1,  0},
            { 1,  0,  0,  0}
    };

    public HermitCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        drawChain(canvas);
        int np = getPoints().size();
        float geom[][] = new float[4][2];
        float mg[][] = new float[4][2];
        float plot[][] = new float[4][2];

        for (int i = 0; i < np - 3; i += 3) {

            geom[0][0] = getPoints().get(i).getX();
            geom[0][1] = getPoints().get(i).getY();
            geom[1][0] = getPoints().get(i + 3).getX();
            geom[1][1] = getPoints().get(i + 3).getY();
            geom[2][0] = getPoints().get(i + 1).getX() - geom[0][0];
            geom[2][1] = getPoints().get(i + 1).getY() - geom[0][1];
            geom[3][0] = geom[1][0] - getPoints().get(i + 2).getX();
            geom[3][1] = geom[1][1] - getPoints().get(i + 2).getY();

            multMatrix(HERMITE_MATRIX, geom, mg);

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

}
