package com.sadvit.math;

import com.sadvit.ui.ApplicationController;

public class PointAdaptor {

    private Point2 point;

    private int depth;

    // TODO пока что так...
    public PointAdaptor(Point3 point3) {
        int x = (int) ((point3.getX() + 1.0) * 250.0);
        int y = ApplicationController.INITIAL_WINDOW_SIZE - (int) ((point3.getY() + 1.0) * 250.0) - 1;
        point = new Point2(x, y);
        depth = (int) ((point3.getZ() + 1.0) * 250.0);
    }

    public Point2 getPoint() {
        return point;
    }

    public int getDepth() {
        return depth;
    }

}
