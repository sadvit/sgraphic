package com.sadvit.event;

import com.sadvit.communication.Event;
import com.sadvit.draw.FillType;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

public class DrawFillEvent implements Event {

    private Point2 point;

    private Color colorOutline;

    private Color colorInlineStart;

    private Color colorInlineEnd;

    private FillType fillType;

    public Point2 getPoint() {
        return point;
    }

    public void setPoint(Point2 point) {
        this.point = point;
    }

    public Color getColorOutline() {
        return colorOutline;
    }

    public void setColorOutline(Color colorOutline) {
        this.colorOutline = colorOutline;
    }

    public Color getColorInlineStart() {
        return colorInlineStart;
    }

    public void setColorInlineStart(Color colorInlineStart) {
        this.colorInlineStart = colorInlineStart;
    }

    public Color getColorInlineEnd() {
        return colorInlineEnd;
    }

    public void setColorInlineEnd(Color colorInlineEnd) {
        this.colorInlineEnd = colorInlineEnd;
    }

    public FillType getFillType() {
        return fillType;
    }

    public void setFillType(FillType fillType) {
        this.fillType = fillType;
    }

    public DrawFillEvent() {
    }

}
