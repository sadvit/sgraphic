package com.sadvit.draw.drawer;

import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

import java.util.Comparator;

public class Edge {

    private Point2 p1;
    private Point2 p2;
    private float m;
    private float curX;

    private double n;
    private double dt;

    private Color color1;
    private Color color2;

    public Color interpolate(int y) {
        int ym = Math.max(p1.getY(), p2.getY());
        double dy = ym - y;
        double t = dy * dt;
        return color1.interpolate(color2, t);
    }

    private Color random() {
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    public Edge(Point2 a, Point2 b) {
        p1 = a;
        p2 = b;
        n = Math.abs(p1.getY() - p2.getY());
        dt = 1 / n;
        m = ((float) (p1.getY() - p2.getY()) / (float) (p1.getX() - p2.getX()));
        color1 = random();
        color2 = random();
    }

    public void activate() {
        curX = p1.getX();
    }

    public void update() {
        curX += ((float) 1 / m);
    }

    public void deactivate() {
        curX = p2.getX();
    }

    public static Comparator<Edge> comparator() {
        return (edge1, edge2) -> edge1.p1.getY() - edge2.p1.getY();
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

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public float getCurX() {
        return curX;
    }

    public void setCurX(float curX) {
        this.curX = curX;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }
}

