package com.sadvit.draw.drawer;

import com.sadvit.event.DrawCircleEvent;
import com.sadvit.event.DrawCurveEvent;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;

public class Drawers {

    /**
     * Возвращает необходимую рисовалку в зависимости от переданного события
     */
    public static Drawer line(DrawLineEvent event) {
        switch (event.getMethodType()) {
            case BRESENHAM:
                switch (event.getBoundType()) {
                    case X4:
                        return new Line4BrezenhamDrawer(event);
                    case X8:
                        return new Line8BrezenhamDrawer(event);
                    default:
                        return null;
                }
            case PARAMETRIC:
                switch (event.getBoundType()) {
                    case X4:
                        return new Line4ParametricDrawer(event);
                    case X8:
                        return new Line8ParametricDrawer(event);
                    default:
                        return null;
                }
            default:
                return null;
        }
    }

    public static Drawer circle(DrawCircleEvent event, SimpleCanvas image) {
        return null;
    }

    public static Drawer curve(DrawCurveEvent event, SimpleCanvas image) {
        return null;
    }

}
