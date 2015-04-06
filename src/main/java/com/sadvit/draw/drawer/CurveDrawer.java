package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.draw.color.PaintBucket;
import com.sadvit.event.DrawCurveEvent;
import com.sadvit.math.Point2;

import java.util.List;

public abstract class CurveDrawer implements Drawer {

    private Brush brush;

    private List<Point2> points;

    protected static final int PRECISION = 10;

    protected float eMatrix[][] = new float[4][4];

    private void calcEMatrix(int prec) {
        // In order to use the "forward difference" method of curve plotting,
        // we must generate this matrix.  The parameter indicates the precision;
        // the number of line segments to use for each curve.

        float step = (float) (1.0 / (float) prec);

        eMatrix[0][0] = 0;
        eMatrix[0][1] = 0;
        eMatrix[0][2] = 0;
        eMatrix[0][3] = 1;

        eMatrix[1][2] = step;
        eMatrix[1][1] = eMatrix[1][2] * step;
        eMatrix[1][0] = eMatrix[1][1] * step;
        eMatrix[1][3] = 0;

        eMatrix[2][0] = 6 * eMatrix[1][0];
        eMatrix[2][1] = 2 * eMatrix[1][1];
        eMatrix[2][2] = 0;
        eMatrix[2][3] = 0;

        eMatrix[3][0] = eMatrix[2][0];
        eMatrix[3][1] = 0;
        eMatrix[3][2] = 0;
        eMatrix[3][3] = 0;
    }

    protected void multMatrix(float m[][], float g[][], float mg[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                mg[i][j] = 0;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 4; k++) {
                    mg[i][j] = mg[i][j] + (m[i][k] * g[k][j]);
                }
            }
        }
    }

    public CurveDrawer(DrawCurveEvent event) {
        this.points = event.getPoints();
        PaintBucket paintBucket = new ColorAdaptor(event.getColor());
        this.brush = BrushFactory.getBrush(event.getBrushType(), paintBucket, event.getBrushSize());
        calcEMatrix(PRECISION);
    }

    protected Brush getBrush() {
        return brush;
    }

    protected List<Point2> getPoints() {
        return points;
    }
}
