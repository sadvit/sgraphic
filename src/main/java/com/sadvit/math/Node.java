package com.sadvit.math;

import java.util.LinkedList;

public class Node {

    private Point2 point;

    private boolean isIntersect;

    private boolean isInside;

    public boolean isInside() {
        return isInside;
    }

    public void setInside(boolean isInside) {
        this.isInside = isInside;
    }

    public boolean isIntersect() {
        return isIntersect;
    }

    public void setIntersect(boolean isIntersect) {
        this.isIntersect = isIntersect;
    }

    @Override
    public String toString() {
        return "N{" + point + " intersect: " + isIntersect + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return point.equals(node.getPoint());
    }

    @Override
    public int hashCode() {
        return point != null ? point.hashCode() : 0;
    }

    public Node(Point2 point) {
        this.point = point;
    }

    public Point2 getPoint() {
        return point;
    }

    public void setPoint(Point2 point) {
        this.point = point;
    }

    public boolean isInside(LinkedList<Node> nodes) {
        return point.isInside(nodes);
    }

}
