package com.sadvit.math;

public class Point2 {

    private int x;

    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point2() {
    }

    public Point2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int length(Point2 p) {
        return Math.max(Math.abs(x - p.getX()), Math.abs(y - p.getY()));
    }

}
