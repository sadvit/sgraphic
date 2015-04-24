package com.sadvit.draw.drawer;

import com.sadvit.math.Point2;
/*
 * Edge.java
 *
 * Created on 7. September 2007, 16:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 * @author Sunshine
 */

/*
 * Just a class for an edge.
 */
public class Edge {

    public Point2 p1;        // first vertice
    public Point2 p2;        // second vertice
    float m;                // slope

    float curX;             // x-coord of intersection with scanline

    /*
     * Create on edge out of two vertices
     */
    public Edge(Point2 a, Point2 b) {
        p1 = a; //new Point(a);
        p2 = b; //new Point(b);

        // m = dy / dx
        m = ((float) (p1.getY() - p2.getY()) / (float) (p1.getX() - p2.getX()));
    }

    /*
     * Called when scanline intersects the first vertice of this edge.
     * That simply means that the intersection point is this vertice.
     */
    public void activate() {
        curX = p1.getX();
    }

    /*
     * Update the intersection point from the scanline and this edge.
     * Instead of explicitly calculate it we just increment with 1/m every time
     * it is intersected by the scanline.
     */
    public void update() {
        curX += (float) ((float) 1 / (float) m);
    }

    /*
     * Called when scanline intersects the second vertice, 
     * so the intersection point is exactly this vertice and from now on 
     * we are done with this edge
     */
    public void deactivate() {
        curX = p2.getX();
    }

}
