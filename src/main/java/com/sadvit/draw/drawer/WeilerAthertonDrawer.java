package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.event.DrawWindowEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.DualList;
import com.sadvit.math.Line;
import com.sadvit.math.Node;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.LinkedList;

@SuppressWarnings("unchecked")
public class WeilerAthertonDrawer implements Drawer {

    private LinkedList<Node> clipPoints = new LinkedList<>();

    private LinkedList<Node> windowPoints = new LinkedList<>();

    private LinkedList<Node> cast(LinkedList<Point2> points) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (points != null && points.size() > 0)
            points.forEach(point -> nodes.add(new Node(point)));
        return nodes;
    }

    public WeilerAthertonDrawer(DrawWindowEvent event) {
        if (event.getClipPoints() != null
                && event.getClipPoints().size() > 0
                && event.getWindowPoints() != null
                && event.getWindowPoints().size() > 0) {
            clipPoints = cast(event.getClipPoints());
            windowPoints = cast(event.getWindowPoints());
            Point2 pl1 = clipPoints.get(0).getPoint();
            Point2 pl2 = windowPoints.get(0).getPoint();
            clipPoints.add(new Node(pl1));
            windowPoints.add(new Node(pl2));
        }
        /*//window
        Point2 w1 = new Point2(100, 100);
        Point2 w2 = new Point2(100, 250);
        Point2 w3 = new Point2(250, 250);
        windowPoints.add(new Node(w1));
        windowPoints.add(new Node(w2));
        windowPoints.add(new Node(w3));
        windowPoints.add(new Node(w1));
        //clip
        Point2 c1 = new Point2(150, 100);
        Point2 c2 = new Point2(150, 200);
        Point2 c3 = new Point2(250, 200);
        clipPoints.add(new Node(c1));
        clipPoints.add(new Node(c2));
        clipPoints.add(new Node(c3));
        clipPoints.add(new Node(c1));*/
    }

    private int getMinXIndex(LinkedList<Node> nodes) {
        int minX = nodes.get(0).getPoint().getX();
        int ix = 0;
        for (int i = 0; i < nodes.size(); i++) {
            int x = nodes.get(i).getPoint().getX();
            if (x < minX) {
                minX = x;
                ix = i;
            }
        }
        return ix;
    }

    private Node cyclicGet(LinkedList<Node> nodes, int i) {
        if (i > nodes.size()) {
            i = 0;
        }
        if (i < 0) {
            i = nodes.size() - 1;
        }
        return nodes.get(i);
    }

    private void normalizeBypass(LinkedList<Node> nodes) {
        int i = getMinXIndex(nodes);
        Point2 p0 = cyclicGet(nodes, i - 1).getPoint();
        Point2 p = cyclicGet(nodes, i).getPoint();
        Point2 p1 = cyclicGet(nodes, i + 1).getPoint();
        if (!(p0.getY() > p.getY() && p.getY() > p1.getY())) {
            Collections.reverse(nodes);
        }
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        clip(canvas);
    }

    private static LinkedList<Node> deepClone(LinkedList<Node> nodes) {
        LinkedList<Node> copy = new LinkedList<>();
        for (Node node : nodes) {
            copy.add(new Node(new Point2(node.getPoint().getX(), node.getPoint().getY())));
        }
        return copy;
    }

    private static LinkedList<Node> deepClone2(LinkedList<Node> nodes) {
        LinkedList<Node> copy = new LinkedList<>();
        for (Node node : nodes) {
            Node _node = new Node(new Point2(node.getPoint().getX(), node.getPoint().getY()));
            _node.setIntersect(node.isIntersect());
            _node.setInside(node.isInside());
            copy.add(_node);
        }
        return copy;
    }

    private void clip(SimpleCanvas canvas) {
        normalizeBypass(windowPoints);
        normalizeBypass(clipPoints);
        inside(clipPoints, windowPoints);
        LinkedList<Node> _windowPoints = intersect(windowPoints, clipPoints);
        LinkedList<Node> _clipPoints = intersect(clipPoints, windowPoints);
        windowPoints = _windowPoints;
        clipPoints = _clipPoints;
        DualList dualList = new DualList(clipPoints, windowPoints);
        LinkedList<LinkedList<Node>> sublists = dualList.getSublists();
        for (LinkedList<Node> sublist : sublists)
            draw(sublist, canvas, Color.GOLD);
        draw(windowPoints, canvas, Color.BLUE);
    }

    private void draw(LinkedList<Node> sublist, SimpleCanvas canvas, Color color) {
        if (sublist.size() > 0) {
            Node node = sublist.get(0);
            sublist.add(node);
        }
        for (int i = 0; i < sublist.size() - 1; i++) {
            Node current = sublist.get(i);
            Node next = sublist.get(i + 1);
            drawLine(current.getPoint(), next.getPoint(), canvas, color);
        }
    }

    private void drawLine(Point2 p1, Point2 p2, SimpleCanvas canvas, Color color) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(color), 1);
        LineDrawer lineDrawer = new Line8ParametricDrawer(p1, p2, brush);
        lineDrawer.draw(canvas);
    }

    private static void inside(LinkedList<Node> clip, LinkedList<Node> window) {
        for (Node node : clip)
            node.setInside(node.isInside(window));
    }

    private static LinkedList<Node> intersect(LinkedList<Node> subject, LinkedList<Node> clip) {
        LinkedList<Node> result = deepClone2(subject);
        for (int i = 0; i < subject.size() - 1; i++) {
            Node current = subject.get(i);
            Node next = subject.get(i + 1);
            Line line = new Line(current, next);
            for (int j = 0; j < clip.size() - 1; j++) {
                Node _current = clip.get(j);
                Node _next = clip.get(j + 1);
                Line _line = new Line(_current, _next);
                Point2 point = _line.crossPoint(line);
                if (point != null) {
                    Node node = new Node(point);
                    node.setIntersect(true);
                    addNodeToList(result, node);
                }
            }
        }
        System.out.println(result);
        return result;
    }

    private static void addNodeToList(LinkedList<Node> nodes, Node node) {
        Point2 p = node.getPoint();
        for (int i = 0; i < nodes.size() - 1; i++) {
            Point2 p1 = nodes.get(i).getPoint();
            Point2 p2 = nodes.get(i + 1).getPoint();
            if (Line.pointInSegment(p, p1 ,p2)) {
                nodes.add(i + 1, node);
                break;
            }
        }
    }

}
