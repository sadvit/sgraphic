package com.sadvit.math;

public class Triangle {

    private Point3 p1;

    private Point3 p2;

    private Point3 p3;

    public Point3 getP1() {
        return p1;
    }

    public void setP1(Point3 p1) {
        this.p1 = p1;
    }

    public Point3 getP2() {
        return p2;
    }

    public void setP2(Point3 p2) {
        this.p2 = p2;
    }

    public Point3 getP3() {
        return p3;
    }

    public void setP3(Point3 p3) {
        this.p3 = p3;
    }

    public Triangle() {
    }

    public Triangle(Point3 p1, Point3 p2, Point3 p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
}
