package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.draw.color.PaintBucket;
import com.sadvit.event.DrawFillEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

import java.util.*;

public class FillScanlineDrawer extends FillDrawer {

    private Color colorInline;

    private Vector<Point2> polygon = new Vector<>();

    private Brush brush;

    public FillScanlineDrawer(List<Point2> points) {
        super(points);
        this.polygon.addAll(points);
        this.polygon.add(points.get(0));
        //this.colorInline = event.getColorInlineStart();
        //this.colorOutline = event.getColorOutline();
        PaintBucket paintBucket = new ColorAdaptor(Color.BLACK);
        this.brush = BrushFactory.getBrush(BrushType.CIRCLE, paintBucket, 1);
    }

    public FillScanlineDrawer(DrawFillEvent event) {
        super(event.getPoints());
        this.polygon.addAll(event.getPoints());
        this.polygon.add(event.getPoints().get(0));
        this.colorInline = event.getColorInlineStart();
        this.colorOutline = event.getColorOutline();
        PaintBucket paintBucket = new ColorAdaptor(Color.BLACK);
        this.brush = BrushFactory.getBrush(BrushType.CIRCLE, paintBucket, 1);
    }

    public List<Edge>  createEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < polygon.size() - 1; i++) {
            if (polygon.elementAt(i).getY() < polygon.elementAt(i + 1).getY())
                edges.add(new Edge(polygon.elementAt(i), polygon.elementAt(i + 1)));
            else
                edges.add(new Edge(polygon.elementAt(i + 1), polygon.elementAt(i)));
        }
        return edges;
    }

    Map<Point2, Color> pcolors = getRandomColors(getPoints());

    private Color interpolate(Edge edge, int y) {
        Color c1 = pcolors.get(edge.getP1());
        Color c2 = pcolors.get(edge.getP2());
        return c2.interpolate(c1, edge.getT(y));
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        List<Edge> edges = createEdges();
        Collections.sort(edges, Edge.comparator());
        int ymax = 0;
        int ymin = edges.get(0).getP1().getY();
        for (Edge edge : edges) {
            if (ymax < edge.getP2().getY())
                ymax = edge.getP2().getY();
        }

        Map<Integer, Color> colors = new HashMap<>();
        ArrayList<Integer> xs = new ArrayList<>();
        for (int y = ymin; y <= ymax; y++) {
            colors.clear();
            xs.clear();
            for (Edge edge : edges) {
                int x = (int) edge.getCurX();
                if (y == edge.getP1().getY()) {
                    if (y == edge.getP2().getY()) {
                        edge.deactivate();
                        xs.add(x);
                        colors.put(x, interpolate(edge, y));
                    } else {
                        edge.activate();
                    }
                }
                if (y == edge.getP2().getY()) {
                    edge.deactivate();
                    xs.add(x);
                    colors.put(x, interpolate(edge, y));
                }
                if (y > edge.getP1().getY() && y < edge.getP2().getY()) {
                    edge.update();
                    xs.add(x);
                    colors.put(x, interpolate(edge, y));
                }
            }
            Collections.sort(xs);
            if (xs.size() < 2 || xs.size() % 2 != 0) {
                continue;
            }
            for (int i = 0; i < xs.size(); i += 2) {
                int x0 = xs.get(i);
                int x1 = xs.get(i + 1);
                fillLine(canvas, x0, x1, y, colors.get(x0), colors.get(x1));
            }
        }
    }

    /*@Override
    public void draw(SimpleCanvas canvas) {
        Edge[] edges = createEdges();
        Edge tmp;
        for (int i = 0; i < edges.length - 1; i++)
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].p1.getY() > edges[j + 1].p1.getY()) {
                    tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        int ymax = 0;
        int ymin = edges[0].p1.getY();
        for (Edge edge : edges) {
            if (ymax < edge.p2.getY())
                ymax = edge.p2.getY();
        }
        Map<Integer, Color> colors = new HashMap<>();
        ArrayList<Integer> xs = new ArrayList<>();
        for (int y = ymin; y <= ymax; y++) {
            colors.clear();
            xs.clear();
            for (Edge edge : edges) {
                int x = (int) edge.curX;
                if (y == edge.p1.getY()) {
                    if (y == edge.p2.getY()) {
                        edge.deactivate();
                        xs.add(x);
                        colors.put(x, edge.interpolate(y));
                    } else {
                        edge.activate();
                    }
                }
                if (y == edge.p2.getY()) {
                    edge.deactivate();
                    xs.add(x);
                    colors.put(x, edge.interpolate(y));
                }
                if (y > edge.p1.getY() && y < edge.p2.getY()) {
                    edge.update();
                    xs.add(x);
                    colors.put(x, edge.interpolate(y));
                }
            }
            Collections.sort(xs);
            if (xs.size() < 2 || xs.size() % 2 != 0) {
                continue;
            }
            for (int i = 0; i < xs.size(); i += 2) {
                int x0 = xs.get(i);
                int x1 = xs.get(i + 1);
                fillLine(canvas, x0, x1, y, colors.get(x0), colors.get(x1));
            }
        }
    }*/

    /*@Override
    public void draw(SimpleCanvas canvas) {
        drawChain(canvas);
        Edge[] sortedEdges = this.createEdges();
        Edge tmp;
        for (int i = 0; i < sortedEdges.length - 1; i++)
            for (int j = 0; j < sortedEdges.length - 1; j++) {
                if (sortedEdges[j].p1.getY() > sortedEdges[j + 1].p1.getY()) {
                    tmp = sortedEdges[j];
                    sortedEdges[j] = sortedEdges[j + 1];
                    sortedEdges[j + 1] = tmp;
                }
            }
        int scanlineEnd = 0;
        for (Edge sortedEdge : sortedEdges) {
            if (scanlineEnd < sortedEdge.p2.getY())
                scanlineEnd = sortedEdge.p2.getY();
        }

        Map<Point2, Color> colors = getRandomColors(polygon);

        ArrayList<Integer> list = new ArrayList<>();
        for (int scanline = sortedEdges[0].p1.getY(); scanline <= scanlineEnd; scanline++) {
            list.clear();
            for (Edge sortedEdge : sortedEdges) {
                if (scanline == sortedEdge.p1.getY()) {
                    if (scanline == sortedEdge.p2.getY()) {
                        sortedEdge.deactivate();
                        list.add((int) sortedEdge.curX);
                    } else {
                        sortedEdge.activate();
                    }
                }
                if (scanline == sortedEdge.p2.getY()) {
                    sortedEdge.deactivate();
                    list.add((int) sortedEdge.curX);
                }
                if (scanline > sortedEdge.p1.getY() && scanline < sortedEdge.p2.getY()) {
                    sortedEdge.update();
                    list.add((int) sortedEdge.curX);
                }
            }
            int swaptmp;
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size() - 1; j++) {
                    if (list.get(j) > list.get(j + 1)) {
                        swaptmp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, swaptmp);
                    }
                }
            }
            if (list.size() < 2 || list.size() % 2 != 0) {
                continue;
            }
            for (int i = 0; i < list.size(); i += 2) {
                fillLine(canvas, list.get(i), list.get(i + 1), scanline, Color.GREEN, Color.RED);
                canvas.setColor(list.get(i), scanline, Color.BLACK);
                canvas.setColor(list.get(i + 1), scanline, Color.BLACK);
            }
        }
    }*/

    private Map<Point2, Color> getRandomColors(List<Point2> points) {
        HashMap<Point2, Color> colors = new HashMap<>();
        for (Point2 point : points) {
            colors.put(point, Color.color(Math.random(), Math.random(), Math.random()));
        }
        return colors;
    }

    private void fillLine(SimpleCanvas canvas, int x1, int x2, int y, Color start, Color end) {
        if (x1 > x2) {
            int t = x1;
            x1 = x2;
            x2 = t;
        }
        double dt = 1.0 / (double) (x2 - x1), t = 0;
        for (int x = x1; x <= x2; x++) {
            t += dt;
            canvas.setColor(x, y, start.interpolate(end, t));
        }
    }

}
