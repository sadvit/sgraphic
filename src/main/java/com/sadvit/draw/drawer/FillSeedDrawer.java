package com.sadvit.draw.drawer;

import com.sadvit.event.DrawFillEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

import java.util.Stack;

public class FillSeedDrawer implements Drawer {

    private Stack<Point2> stack = new Stack<>();

    private Point2 seed;

    public FillSeedDrawer(Point2 point) {
        this.seed = point;
    }

    public FillSeedDrawer(DrawFillEvent event) {
        this.seed = event.getPoint();
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        stack.push(seed);
        while (!stack.empty()) {

            Point2 point = stack.pop();
            canvas.setColor(point, Color.BLACK);

            Point2 pl = new Point2(point.getX() - 1, point.getY());
            Point2 pu = new Point2(point.getX(), point.getY() - 1);
            Point2 pr = new Point2(point.getX() + 1, point.getY());
            Point2 pd = new Point2(point.getX(), point.getY() + 1);

            try {
                if (!canvas.getColor(pl).equals(Color.BLACK))
                    stack.push(pl);
                if (!canvas.getColor(pu).equals(Color.BLACK))
                    stack.push(pu);
                if (!canvas.getColor(pr).equals(Color.BLACK))
                    stack.push(pr);
                if (!canvas.getColor(pd).equals(Color.BLACK))
                    stack.push(pd);
            } catch (Exception ignored) {

            }

        }
    }

}
