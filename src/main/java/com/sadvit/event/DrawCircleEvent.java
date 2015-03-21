package com.sadvit.event;

import com.sadvit.communication.Event;
import com.sadvit.draw.BoundType;
import com.sadvit.draw.FigureType;
import com.sadvit.draw.MethodType;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

public class DrawCircleEvent implements Event {

    private MethodType methodType;

    private Point2 coordinates;

    private Color color;

    private int radius;

    private int brushSize;

    private FigureType figureType;

    private BoundType boundType;

    private BrushType brushType;

    public MethodType getMethodType() {
        return methodType;
    }

    public void setMethodType(MethodType methodType) {
        this.methodType = methodType;
    }

    public Point2 getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2 coordinates) {
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public FigureType getFigureType() {
        return figureType;
    }

    public void setFigureType(FigureType figureType) {
        this.figureType = figureType;
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

    public DrawCircleEvent() {
    }

}
