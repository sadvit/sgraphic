package com.sadvit.math;

public class Line {

    private int a;

    private int b;

    private int c;

    private Point2 p1;

    private Point2 p2;

    public Line(Point2 p1, Point2 p2) {
        this.p1 = p1;
        this.p2 = p2;
        a = p1.getY() - p2.getY();
        b = p2.getX() - p1.getX();
        c = p1.getX() * p2.getY() - p2.getX() * p1.getY();
    }

    public Line(Node node1, Node node2) {
        this(node1.getPoint(), node2.getPoint());
    }

    public Point2 getP1() {
        return p1;
    }

    public void setP1(Point2 p1) {
        this.p1 = p1;
    }

    public Point2 getP2() {
        return p2;
    }

    public void setP2(Point2 p2) {
        this.p2 = p2;
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

    public Point2 crossPoint(Line line) {
        return intersect(this, line);
    }

    public static Point2 intersect(Line line1, Line line2) {
        float x1 = line1.getP1().getX(),
                x2 = line1.getP2().getX(),
                x3 = line2.getP1().getX(),
                x4 = line2.getP2().getX(),
                y1 = line1.getP1().getY(),
                y2 = line1.getP2().getY(),
                y3 = line2.getP1().getY(),
                y4 = line2.getP2().getY();
        float d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0)
            return null;
        float xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        float yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        Point2 point = new Point2(xi, yi);
        return (pointInSegment(point, line1.getP1(), line1.getP2()) && pointInSegment(point, line2.getP1(), line2.getP2())) ? point : null;
    }

    public static boolean pointInSegment(Point2 p, Point2 p1, Point2 p2) {
        int maxX = Math.max(p1.getX(), p2.getX());
        int maxY = Math.max(p1.getY(), p2.getY());
        int minX = Math.min(p1.getX(), p2.getX());
        int minY = Math.min(p1.getY(), p2.getY());
        int x = p.getX();
        int y = p.getY();
        return x <= maxX && x >= minX && y <= maxY && y >= minY;
    }

    @Override
    public String toString() {
        return "Line{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
