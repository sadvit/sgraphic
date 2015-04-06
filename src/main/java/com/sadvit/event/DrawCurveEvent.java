package com.sadvit.event;

import com.sadvit.communication.Event;
import com.sadvit.draw.CurveType;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class DrawCurveEvent implements Event {

    private ArrayList<Point2> points;

    private Color color;

    private CurveType curveType;

    private BrushType brushType;

    private int brushSize;

    public ArrayList<Point2> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point2> points) {
        this.points = points;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public CurveType getCurveType() {
        return curveType;
    }

    public void setCurveType(CurveType curveType) {
        this.curveType = curveType;
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

    public DrawCurveEvent() {
    }

}
