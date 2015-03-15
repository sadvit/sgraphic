package com.sadvit.event;


import com.sadvit.communication.Event;
import com.sadvit.draw.BoundType;
import com.sadvit.draw.BrushType;
import com.sadvit.draw.LineType;
import com.sadvit.draw.MethodType;
import com.sadvit.math.Point;
import javafx.scene.paint.Color;

public class DrawLineEvent implements Event {

    private MethodType methodType;

    private Point p1;

    private Point p2;

    private Color colorStart;

    private Color colorEnd;

    private LineType lineType;

    private BoundType boundType;

    private BrushType brushType;

    private int brushSize;

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Color getColorStart() {
        return colorStart;
    }

    public void setColorStart(Color colorStart) {
        this.colorStart = colorStart;
    }

    public Color getColorEnd() {
        return colorEnd;
    }

    public void setColorEnd(Color colorEnd) {
        this.colorEnd = colorEnd;
    }

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public BoundType getBoundType() {
        return boundType;
    }

    public void setBoundType(BoundType boundType) {
        this.boundType = boundType;
    }

    public BrushType getBrushType() {
        return brushType;
    }

    public void setBrushType(BrushType brushType) {
        this.brushType = brushType;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public DrawLineEvent() {

    }

    @Override
    public String toString() {
        return "DrawLineEvent{" +
                "methodType=" + methodType +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", colorStart=" + colorStart +
                ", colorEnd=" + colorEnd +
                ", lineType=" + lineType +
                ", boundType=" + boundType +
                ", brushType=" + brushType +
                ", brushSize=" + brushSize +
                '}';
    }
}
