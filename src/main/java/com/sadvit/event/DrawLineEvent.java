package com.sadvit.event;


import com.sadvit.communication.Event;
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

}
