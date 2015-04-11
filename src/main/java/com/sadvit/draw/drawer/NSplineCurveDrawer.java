package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCurveEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import com.sadvit.ui.ApplicationController;

public class NSplineCurveDrawer extends CurveDrawer {

    @Override
    public void draw(SimpleCanvas canvas) {

        int w = ApplicationController.INITIAL_WINDOW_SIZE;

        int n = getPoints().size() - 1;
        int k1 = 3;

        int n1 = n + 1;
        int nt = n + k1 + 1;
        double[] px = new double[n1];
        double[] py = new double[n1];
        double[] wi = new double[n1];
        double[] ti = new double[nt + k1];
        double[][] n2 = new double[nt + 1][w];

        for (int i = 0; i < n1; i++) {
            px[i] = getPoints().get(i).getX();
            py[i] = getPoints().get(i).getY();
            wi[i] = 1;
        }

        double value = 0;
        for (int i = 0; i < nt; i++) {
            value += 0.1;
            ti[i] = value;
        }

        double to = ti[0], dt = ti[nt - 1] - to;
        for (int i = 0; i < nt; i++) {
            ti[i] = w + w * (ti[i] - to) / dt;
        }

        double step = (ti[nt - 1] - ti[0]) / (w - .9), t = ti[0];
        int tmin = (int) ((ti[k1 - 1] - ti[0]) / step) + 1;
        int tmax = (int) ((ti[n1] - ti[0]) / step);
        int i1 = 0;
        for (int l = 0; l < w; l++) {
            while (t >= ti[i1]) i1++;
            int i = i1 - 1;
            for (int j = 0; j < nt; j++) n2[j][l] = 0;
            n2[i][l] = 1;
            for (int m = 2; m <= k1; m++) {
                int jb = i - m + 1;
                if (jb < 0) jb = 0;
                for (int j = jb; j <= i; j++) {
                    n2[j][l] = n2[j][l] * (t - ti[j]) / (ti[j + m - 1] - ti[j]) +
                            n2[j + 1][l] * (ti[j + m] - t) / (ti[j + m] - ti[j + 1]);
                }
            }
            t += step;
        }

        int X, Y;
        double sX = 0, sY = 0, sW = 0;
        for (int j = 0; j < n1; j++) {
            sX += px[j] * wi[j] * n2[j][tmin];
            sY += py[j] * wi[j] * n2[j][tmin];
            sW += wi[j] * n2[j][tmin];
        }
        int Xold = (int) (sX / sW), Yold = (int) (sY / sW);
        for (int k = tmin + 1; k <= tmax; k++) {
            sX = 0;
            sY = 0;
            sW = 0;
            for (int j = 0; j < n1; j++) {
                sX += px[j] * wi[j] * n2[j][k];
                sY += py[j] * wi[j] * n2[j][k];
                sW += wi[j] * n2[j][k];
            }
            X = (int) (sX / sW);
            Y = (int) (sY / sW);
            if ((X < w) && (Xold < w)) {
                new Line8ParametricDrawer(new Point2(Xold, Yold), new Point2(X, Y), getBrush()).draw(canvas);
            }
            Xold = X;
            Yold = Y;
        }
    }

    public NSplineCurveDrawer(DrawCurveEvent event) {
        super(event);
    }

}