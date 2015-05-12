package com.sadvit.math;

public class Line {

    private int a;

    private int b;

    private int c;

    public Line(Point2 p1, Point2 p2) {
        a = p1.getY() - p2.getY();
        b = p2.getX() - p1.getX();
        c = p1.getX() * p2.getY() - p2.getX() * p1.getY();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public boolean isCross(Line line) {
        return a / b != line.getA() / line.getB();
    }

}
