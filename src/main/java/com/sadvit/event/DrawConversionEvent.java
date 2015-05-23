package com.sadvit.event;

import com.sadvit.communication.Event;
import com.sadvit.math.Point2;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.LinkedList;

public class DrawConversionEvent implements Event {

    private RealMatrix matrix;

    private LinkedList<Point2> points;

    public LinkedList<Point2> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point2> points) {
        this.points = points;
    }

    public RealMatrix getMatrix() {
        return matrix;
    }

    public void setMatrix(RealMatrix matrix) {
        this.matrix = matrix;
    }
}
