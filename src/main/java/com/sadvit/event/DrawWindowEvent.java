package com.sadvit.event;

import com.sadvit.communication.Event;
import com.sadvit.draw.AmputationType;
import com.sadvit.math.Point2;

import java.util.LinkedList;

public class DrawWindowEvent implements Event {

    private AmputationType type;

    private Point2 p1;

    private Point2 p2;

    private LinkedList<Point2> clipPoints;

    public LinkedList<Point2> getWindowPoints() {
        return windowPoints;
    }

    public void setWindowPoints(LinkedList<Point2> windowPoints) {
        this.windowPoints = windowPoints;
    }

    private LinkedList<Point2> windowPoints;

    public LinkedList<Point2> getClipPoints() {
        return clipPoints;
    }

    public void setClipPoints(LinkedList<Point2> clipPoints) {
        this.clipPoints = clipPoints;
    }

    public AmputationType getType() {
        return type;
    }

    public void setType(AmputationType type) {
        this.type = type;
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

    public DrawWindowEvent() {
    }

}
