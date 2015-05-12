package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.event.DrawWindowEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SutherlandHodgmanDrawer implements Drawer {

    private LinkedList<Point2> polygon = new LinkedList<>();
    private LinkedList<Point2> clippedPoly = new LinkedList<>();

    private int leftEdge;
    private int rightEdge;
    private int topEdge;
    private int bottomEdge;

    public static final byte ID_TOPEDGE = 0;
    public static final byte ID_RIGHTEDGE = 1;
    public static final byte ID_BOTTOMEDGE = 2;
    public static final byte ID_LEFTEDGE = 3;

    public SutherlandHodgmanDrawer(DrawWindowEvent event) {
        LinkedList<Point2> points = event.getClipPoints();
        if (!points.getFirst().equals(points.getLast())) {
            points.add(points.getFirst());
        }
        polygon = points;
        leftEdge = event.getP1().getX();
        rightEdge = event.getP2().getX();
        topEdge = event.getP1().getY();
        bottomEdge = event.getP2().getY();
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        canvas.clean();
        if (polygon.size() > 2) {
            drawLine(rightEdge, topEdge, leftEdge, topEdge, canvas);
            drawLine(rightEdge, bottomEdge, leftEdge, bottomEdge, canvas);
            drawLine(rightEdge, topEdge, rightEdge, bottomEdge, canvas);
            drawLine(leftEdge, topEdge, leftEdge, bottomEdge, canvas);
            clip();
            drawPolygon(canvas, clippedPoly);
        }
    }

    private boolean isInsideClipWindow(Point2 p, byte EdgeID) {
        if (EdgeID == ID_TOPEDGE)
            return (p.getY() >= topEdge);
        else if (EdgeID == ID_BOTTOMEDGE)
            return (p.getY() <= bottomEdge);
        else if (EdgeID == ID_LEFTEDGE)
            return (p.getX() >= leftEdge);
        else
            return EdgeID == ID_RIGHTEDGE && (p.getX() <= rightEdge);
    }

    private Point2 intersectionPoint(byte EdgeID, Point2 p1, Point2 p2) {
        if (EdgeID == ID_TOPEDGE)
            return new Point2(p1.getX() + (topEdge - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()), topEdge);
        else if (EdgeID == ID_BOTTOMEDGE)
            return new Point2(p1.getX() + (bottomEdge - p1.getY()) * (p2.getX() - p1.getX()) / (p2.getY() - p1.getY()), bottomEdge);
        else if (EdgeID == ID_RIGHTEDGE)
            return new Point2(rightEdge, p1.getY() + (rightEdge - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
        else if (EdgeID == ID_LEFTEDGE)
            return new Point2(leftEdge, p1.getY() + (leftEdge - p1.getX()) * (p2.getY() - p1.getY()) / (p2.getX() - p1.getX()));
        return null;
    }

    public void clip() {
        if (polygon.size() < 3) return;
        clippedPoly.clear();
        Point2 p;
        Point2 p2;
        List<Point2> workPoly = (List<Point2>) polygon.clone();
        for (byte edgeID = 0; edgeID < 4; edgeID++) {
            clippedPoly.clear();
            for (int i = 0; i < workPoly.size() - 1; i++) {
                p = workPoly.get(i);
                p2 = workPoly.get(i + 1);
                if (isInsideClipWindow(p, edgeID)) {
                    if (isInsideClipWindow(p2, edgeID))
                        clippedPoly.add(new Point2(p2.getX(), p2.getY()));
                    else {
                        clippedPoly.add(intersectionPoint(edgeID, p, p2));
                    }
                } else {
                    if (isInsideClipWindow(p2, edgeID)) {
                        clippedPoly.add(intersectionPoint(edgeID, p, p2));
                        clippedPoly.add(new Point2(p2.getX(), p2.getY()));
                    }
                }
            }
            if (clippedPoly.getFirst() != clippedPoly.getLast())
                clippedPoly.add(clippedPoly.getFirst());
            workPoly = (List<Point2>) clippedPoly.clone();
        }
    }

    private void drawLine(int x0, int y0, int x1, int y1, SimpleCanvas canvas) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(javafx.scene.paint.Color.BLACK), 1);
        LineDrawer lineDrawer = new ModBrezenhamDrawer(new Point2(x0, y0), new Point2(x1, y1), brush);
        lineDrawer.draw(canvas);
    }

    private void drawLine(Point2 p1, Point2 p2, SimpleCanvas canvas) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(javafx.scene.paint.Color.BLACK), 1);
        LineDrawer lineDrawer = new ModBrezenhamDrawer(p1, p2, brush);
        lineDrawer.draw(canvas);
    }

    public void drawPolygon(SimpleCanvas canvas, LinkedList<Point2> points) {
        Point2 p;
        if (points.size() < 1) return;
        Point2 p2;
        for (int i = 0; i < points.size() - 1; i++) {
            p = points.get(i);
            p2 = points.get(i + 1);
            drawLine(p, p2, canvas);
        }
    }

}
