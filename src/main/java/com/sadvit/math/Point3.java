package com.sadvit.math;

public class Point3 {

    private double x;

    private double y;

    private double z;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Point3() {
    }

    public Point3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Point3 parsePoint(String x, String y, String z) {
        return new Point3(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
    }

}
