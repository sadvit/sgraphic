package com.sadvit.math;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix {

    public static RealMatrix getScale(double a, double b) {
        return MatrixUtils.createRealMatrix(new double[][]{
                {a, 0, 0},
                {0, b, 0},
                {0, 0, 1}
        });
    }

    public static RealMatrix getMove(double a, double b) {
        return MatrixUtils.createRealMatrix(new double[][]{
                {1, 0, a},
                {0, 1, b},
                {0, 0, 1}
        });
    }

    public static RealMatrix getRotate(double f) {
        return MatrixUtils.createRealMatrix(new double[][]{
                { cos(f), sin(f), 0},
                {-sin(f), cos(f), 0},
                {0      , 0     , 1}
        });
    }

    public static RealMatrix getReflect(double a, double b, double c) {
        double div = (b * b + a * a);
        return MatrixUtils.createRealMatrix(new double[][]{
                {(b * b - a * a)/div, (-2 * a * b)/div, (-2 * a * c)/div},
                {(-2 * a * b)/div, (a * a - b * b)/div, (-2 * b * c)/div},
                {0               , 0                  ,                1}
        });
    }

    public static RealMatrix getE() {
        return MatrixUtils.createRealMatrix(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });
    }

}
