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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
        PaintBucket paintBucket = new ColorAdaptor(colorInline);
        this.brush = BrushFactory.getBrush(BrushType.CIRCLE, paintBucket, 1);
    }

    public FillScanlineDrawer(DrawFillEvent event) {
        super(event.getPoints());
        this.polygon.addAll(event.getPoints());
        this.polygon.add(event.getPoints().get(0));
        this.colorInline = event.getColorInlineStart();
        this.colorOutline = event.getColorOutline();
        PaintBucket paintBucket = new ColorAdaptor(colorInline);
        this.brush = BrushFactory.getBrush(BrushType.CIRCLE, paintBucket, 1);
    }

    public Edge[] createEdges() {
        Edge[] sortedEdges = new Edge[polygon.size() - 1];
        for (int i = 0; i < polygon.size() - 1; i++) {
            //if (polygon.elementAt(i).y == polygon.elementAt(i+1).y) continue;
            if (polygon.elementAt(i).getY() < polygon.elementAt(i + 1).getY())
                sortedEdges[i] = new Edge(polygon.elementAt(i), polygon.elementAt(i + 1));
            else
                sortedEdges[i] = new Edge(polygon.elementAt(i + 1), polygon.elementAt(i));
        }
        return sortedEdges;
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        drawChain(canvas);
        // create edges array from polygon vertice vector
        // make sure that first vertice of an edge is the smaller one
        Edge[] sortedEdges = this.createEdges();
        // sort all edges by y coordinate, smallest one first, lousy bubblesort
        Edge tmp;
        for (int i = 0; i < sortedEdges.length - 1; i++)
            for (int j = 0; j < sortedEdges.length - 1; j++) {
                if (sortedEdges[j].p1.getY() > sortedEdges[j + 1].p1.getY()) {
                    // swap both edges
                    tmp = sortedEdges[j];
                    sortedEdges[j] = sortedEdges[j + 1];
                    sortedEdges[j + 1] = tmp;
                }
            }
        // find biggest y-coord of all vertices
        int scanlineEnd = 0;
        for (Edge sortedEdge : sortedEdges) {
            if (scanlineEnd < sortedEdge.p2.getY())
                scanlineEnd = sortedEdge.p2.getY();
        }
        // scanline starts at smallest y coordinate
        int scanline;
        // this list holds all cutpoints from current scanline with the polygon
        ArrayList<Integer> list = new ArrayList<>();
        // move scanline step by step down to biggest one
        for (scanline = sortedEdges[0].p1.getY(); scanline <= scanlineEnd; scanline++) {
            list.clear();
            // loop all edges to see which are cut by the scanline
            for (Edge sortedEdge : sortedEdges) {
                // here the scanline intersects the smaller vertice
                if (scanline == sortedEdge.p1.getY()) {
                    if (scanline == sortedEdge.p2.getY()) {
                        // the current edge is horizontal, so we add both vertices
                        sortedEdge.deactivate();
                        list.add((int) sortedEdge.curX);
                    } else {
                        sortedEdge.activate();
                        // we don't insert it in the list cause this vertice is also
                        // the (bigger) vertice of another edge and already handled
                    }
                }
                // here the scanline intersects the bigger vertice
                if (scanline == sortedEdge.p2.getY()) {
                    sortedEdge.deactivate();
                    list.add((int) sortedEdge.curX);
                }
                // here the scanline intersects the edge, so calc intersection point
                if (scanline > sortedEdge.p1.getY() && scanline < sortedEdge.p2.getY()) {
                    sortedEdge.update();
                    list.add((int) sortedEdge.curX);
                }
            }
            // now we have to sort our list with our x-coordinates, ascendend
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
            // so draw all line segments on current scanline
            for (int i = 0; i < list.size(); i += 2) {
                //new Line8ParametricDrawer(new Point2(list.get(i), scanline), new Point2(list.get(i + 1), scanline), brush).draw(canvas);
                fillLine(canvas, list.get(i), list.get(i + 1), scanline, Color.GREEN, Color.RED);
                canvas.setColor(list.get(i), scanline, Color.BLACK);
                canvas.setColor(list.get(i + 1), scanline, Color.BLACK);
            }
        }
    }

    private void fillLine(SimpleCanvas canvas, int x1, int x2, int y, Color start, Color end) {
        if (x1 > x2) {
            int t = x1;
            x1 = x2;
            x2 = t;
        }
        double dt = 1.0 / (double) (x2 - x1), t = 0;
        for (int x = x1; x <= x2; x++) {
            canvas.setColor(x, y, start.interpolate(end, t));
            t += dt;
        }
    }

}
