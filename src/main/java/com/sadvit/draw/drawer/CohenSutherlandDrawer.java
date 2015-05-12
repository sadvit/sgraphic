package com.sadvit.draw.drawer;

import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.event.DrawWindowEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

public class CohenSutherlandDrawer implements Drawer {

    private Point2 P0;

    private Point2 P1;

    private int yTop, yBottom, xRight, xLeft;

    public CohenSutherlandDrawer(DrawWindowEvent event) {
        if (event.getClipPoints().size() >= 2) {
            P0 = event.getClipPoints().get(0);
            P1 = event.getClipPoints().get(1);
            yTop = event.getP2().getY();
            xLeft = event.getP1().getX();
            yBottom = event.getP1().getY();
            xRight = event.getP2().getX();
        }
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        canvas.clean();
        if (P0 != null && P1 != null) {
            drawLine(xRight, yTop, xLeft, yTop, canvas);
            drawLine(xRight, yBottom, xLeft, yBottom, canvas);
            drawLine(xRight, yTop, xRight, yBottom, canvas);
            drawLine(xLeft, yTop, xLeft, yBottom, canvas);
            if (amputate()) {
                drawLine(P0, P1, canvas);
            }
        }
    }

    private void drawLine(int x0, int y0, int x1, int y1, SimpleCanvas canvas) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(Color.BLACK), 1);
        LineDrawer lineDrawer = new ModBrezenhamDrawer(new Point2(x0, y0), new Point2(x1, y1), brush);
        lineDrawer.draw(canvas);
    }

    private void drawLine(Point2 p1, Point2 p2, SimpleCanvas canvas) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(Color.BLACK), 1);
        LineDrawer lineDrawer = new ModBrezenhamDrawer(p1, p2, brush);
        lineDrawer.draw(canvas);
    }

    private int outCodes(Point2 P) {
        int Code = 0;

        if (P.getY() > yTop) Code += 1; /* code for above */
        else if (P.getY() < yBottom) Code += 2; /* code for below */

        if (P.getX() > xRight) Code += 4; /* code for right */
        else if (P.getX() < xLeft) Code += 8; /* code for left */

        return Code;
    }

    private static boolean rejectCheck(int outCode1, int outCode2) {
        return (outCode1 & outCode2) != 0;
    }


    private static boolean acceptCheck(int outCode1, int outCode2) {
        return (outCode1 == 0) && (outCode2 == 0);
    }

    private boolean amputate() {
        int outCode0, outCode1;
        while (true) {
            outCode0 = outCodes(P0);
            outCode1 = outCodes(P1);
            if (rejectCheck(outCode0, outCode1)) return false;
            if (acceptCheck(outCode0, outCode1)) return true;
            if (outCode0 == 0) {
                double tempCoord;
                int tempCode;
                tempCoord = P0.getX();
                P0.setX(P1.getX());
                P1.setX((int) tempCoord);
                tempCoord = P0.getY();
                P0.setY(P1.getY());
                P1.setY((int) tempCoord);
                tempCode = outCode0;
                outCode0 = outCode1;
                outCode1 = tempCode;
            }
            if ((outCode0 & 1) != 0) {
                P0.setX(P0.getX() + (P1.getX() - P0.getX()) * (yTop - P0.getY()) / (P1.getY() - P0.getY()));
                P0.setY(yTop);
            } else if ((outCode0 & 2) != 0) {
                P0.setX(P0.getX() + (P1.getX() - P0.getX()) * (yBottom - P0.getY()) / (P1.getY() - P0.getY()));
                P0.setY(yBottom);
            } else if ((outCode0 & 4) != 0) {
                P0.setY(P0.getY() + (P1.getY() - P0.getY()) * (xRight - P0.getX()) / (P1.getX() - P0.getX()));
                P0.setX(xRight);
            } else if ((outCode0 & 8) != 0) {
                P0.setY(P0.getY() + (P1.getY() - P0.getY()) * (xLeft - P0.getX()) / (P1.getX() - P0.getX()));
                P0.setX(xLeft);
            }
        }
    }

}
