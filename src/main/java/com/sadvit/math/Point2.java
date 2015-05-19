package com.sadvit.math;

import java.util.LinkedList;

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
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public boolean isInside(LinkedList<Node> nodes) {
        if (nodes.size() <= 1)
            return false;

        int intersectionsNum = 0;
        int prev = nodes.size() - 1;
        boolean prevUnder = nodes.get(prev).getPoint().getY() < getY();

        for (int i = 0; i < nodes.size(); ++i) {
            boolean curUnder = nodes.get(i).getPoint().getY() < getY();

            Point2 a = nodes.get(prev).getPoint().minus(this);
            Point2 b = nodes.get(i).getPoint().minus(this);

            float t = (a.getX() * (b.getY() - a.getY()) - a.getY() * (b.getX() - a.getX()));
            if (curUnder && !prevUnder) {
                if (t > 0)
                    intersectionsNum += 1;
            }
            if (!curUnder && prevUnder) {
                if (t < 0)
                    intersectionsNum += 1;
            }

            prev = i;
            prevUnder = curUnder;
        }
        return (intersectionsNum & 1) != 0;
    }

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int length(Point2 p) {
        return Math.max(Math.abs(x - p.getX()), Math.abs(y - p.getY()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point2 point2 = (Point2) o;

        return x == point2.x && y == point2.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public Point2 minus(Point2 point) {
        return new Point2(x - point.getX(), y - point.getY());
    }

}
