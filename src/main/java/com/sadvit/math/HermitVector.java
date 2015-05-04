package com.sadvit.math;

public class HermitVector {

    private double X = 0;
    private double Y = 0;

    public HermitVector(double x, double y) {
        X = x;
        Y = y;
    }

    public HermitVector() {
        X = 0;
        Y = 0;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setVector(HermitVector v) {
        X = v.getX();
        Y = v.getY();
    }

}
