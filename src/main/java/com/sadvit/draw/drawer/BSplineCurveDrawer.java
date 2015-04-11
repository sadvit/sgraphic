package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCurveEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

public class BSplineCurveDrawer extends CurveDrawer {

    private static final float MULT = (float) (1.0 / 6.0);

    private static final float BSPLINE_MATRIX[][] = {
            {    -MULT,  3 * MULT, -3 * MULT, MULT},
            { 3 * MULT, -6 * MULT,  3 * MULT,    0},
            {-3 * MULT,         0,  3 * MULT,    0},
            {     MULT,  4 * MULT,      MULT,    0}
    };

    public BSplineCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        drawChain(canvas);
        int np = getPoints().size();
        float geom[][] = new float[4][2];
        float mg[][] = new float[4][2];
        float plot[][] = new float[4][2];

        for (int i = 0; i < np - 3; i++) {

            for (int j = 3; j >= 0; j--) {
                geom[3 - j][0] = getPoints().get(i + j).getX();
                geom[3 - j][1] = getPoints().get(i + j).getY();
            }
            multMatrix(BSPLINE_MATRIX, geom, mg);
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
