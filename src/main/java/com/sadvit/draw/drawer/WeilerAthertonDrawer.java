package com.sadvit.draw.drawer;

import com.sadvit.event.DrawWindowEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

import java.util.LinkedList;

public class WeilerAthertonDrawer implements Drawer {

    private LinkedList<Point2> clipPoints;

    private LinkedList<Point2> windowPoints;

    public WeilerAthertonDrawer(DrawWindowEvent event) {
        this.clipPoints = event.getClipPoints();
        this.windowPoints = event.getWindowPoints();
    }

    @Override
    public void draw(SimpleCanvas canvas) {

    }

    private void cross() {

    }

}
