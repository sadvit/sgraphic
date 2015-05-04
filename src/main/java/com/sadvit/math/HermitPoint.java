package com.sadvit.math;

public class HermitPoint {

    private double X = 0;
    private double Y = 0;
    private HermitVector direction = new HermitVector();

    public HermitPoint(double x, double y) {
        X = x;
        Y = - y;
    }

    public HermitPoint() {

    }

    public void setDirection(HermitVector v) {
        direction.setVector(v);
    }

    public HermitVector getDirection() {
        return direction;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public void addHermitPoint(double k, HermitPoint point) {
        X = X + k * point.getX();
        Y = Y + k * point.getY();
    }

    public void addHermitVector(double k, HermitVector v) {
        X = X + k * v.getX();
        Y = Y + k * v.getY();
    }

}
