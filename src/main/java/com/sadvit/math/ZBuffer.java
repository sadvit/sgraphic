package com.sadvit.math;

import com.sadvit.image.SimpleCanvas;
import javafx.scene.paint.Color;

public class ZBuffer implements SimpleCanvas {

    private int[][] array;

    public ZBuffer(int width, int height) {
        array = new int[width][height];
    }

    public void setPoint(Point3 point) {
        PointAdaptor adaptor = new PointAdaptor(point);
        int x = adaptor.getPoint().getX();
        int y = adaptor.getPoint().getY();
        int depth = adaptor.getDepth();

        int value = array[x][y];
        if (value < depth) array[x][y] = depth;
    }

    @Override
    public Color getColor(int x, int y) {
        return null;
    }

    @Override
    public void setColor(int x, int y, Color color) {

    }

    @Override
    public Color getColor(Point2 point) {
        return null;
    }

    @Override
    public void setColor(Point2 point, Color color) {

    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void clean() {

    }
}
