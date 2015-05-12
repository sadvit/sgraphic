package com.sadvit.draw.drawer;

import com.sadvit.event.*;

public class Drawers {

    public static Drawer line(DrawLineEvent event) {
        if (event.getSmooth() != null) {
            switch (event.getSmooth()) {
                case BR_SMOOTH:
                    return new ModBrezenhamDrawer(event);
                case MASK_SMOOTH:
                    return new BlurLineDrawer(event);
                case WU_SMOOTH:
                    return new WuLineDrawer(event);
                default:
                    break;
            }
        }
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

    public static Drawer circle(DrawCircleEvent event) {
        switch (event.getMethodType()) {
            case PARAMETRIC:
                return new CircleParametricDrawer(event);
            case BRESENHAM:
                return new CircleBrezenhamDrawer(event);
        }
        return null;
    }

    public static Drawer curve(DrawCurveEvent event) {
        switch (event.getCurveType()) {
            case BEZIER:
                return new BezierCurveDrawer(event);
            case HERMITE:
                return new HermitCurveDrawer(event);
            case BSPLINE:
                return new BSplineCurveDrawer(event);
            case NURBS:
                return new NSplineCurveDrawer(event);
            default:
                return null;
        }
    }

    public static Drawer fill(DrawFillEvent event) {
        switch (event.getFillType()) {
            case SCANLINE:
                return new FillScanlineDrawer(event);
            case SECTION:
                return new FillSectionDrawer(event);
            case SEED:
                return new FillSeedDrawer(event);
            default:
                return null;
        }
    }

    public static Drawer amputate(DrawWindowEvent event) {
        switch (event.getType()) {
            case COHEN_SUTHERLAND:
                return new CohenSutherlandDrawer(event);
            case SUTHERLAND_HODGMAN:
                return new SutherlandHodgmanDrawer(event);
            case WEILER_ATHERTON:
                return new WeilerAthertonDrawer(event);
            default:
                return null;
        }
    }

}
