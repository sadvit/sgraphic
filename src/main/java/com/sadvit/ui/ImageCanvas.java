package com.sadvit.ui;

import com.sadvit.communication.EventBus;
import com.sadvit.dialog.Dialogs;
import com.sadvit.draw.brush.Brush;
import com.sadvit.draw.brush.BrushFactory;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.color.ColorAdaptor;
import com.sadvit.draw.drawer.LineDrawer;
import com.sadvit.draw.drawer.ModBrezenhamDrawer;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ImageCanvas extends Canvas {

    private SimpleCanvas image;

    private Point2 click;

    private boolean firstClick = true;

    private void drawClick(MouseEvent event, Color color) {
        image.setColor(new Point2(event.getX(), event.getY()), color);
        image.setColor(new Point2(event.getX() - 1, event.getY()), color);
        image.setColor(new Point2(event.getX() - 2, event.getY()), color);
        image.setColor(new Point2(event.getX(), event.getY() + 1), color);
        image.setColor(new Point2(event.getX(), event.getY() + 2), color);
        image.setColor(new Point2(event.getX() + 1, event.getY()), color);
        image.setColor(new Point2(event.getX() + 2, event.getY()), color);
        image.setColor(new Point2(event.getX(), event.getY() - 1), color);
        image.setColor(new Point2(event.getX(), event.getY() - 2), color);
        redraw();
    }

    private Point2 prev;

    private void drawChain() {

    }

    private void drawLine(Point2 p1, Point2 p2, SimpleCanvas canvas) {
        drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), canvas);
    }

    private void drawLine(int x0, int y0, int x1, int y1, SimpleCanvas canvas) {
        Brush brush = BrushFactory.getBrush(BrushType.CIRCLE, new ColorAdaptor(javafx.scene.paint.Color.BLACK), 1);
        LineDrawer lineDrawer = new ModBrezenhamDrawer(new Point2(x0, y0), new Point2(x1, y1), brush);
        lineDrawer.draw(canvas);
    }

    public ImageCanvas() {
        widthProperty().addListener(evt -> redraw());
        heightProperty().addListener(evt -> redraw());
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Dialogs.getCurveDialog() != null) {
                Dialogs.getCurveDialog().addPoint(event.getX(), event.getY());
                drawClick(event, Color.RED);
                if (firstClick) {
                    firstClick = false;
                    click = new Point2((int)event.getX(), (int)event.getY());
                } else {
                    firstClick = true;
                    Point2 point = new Point2((int)event.getX(), (int)event.getY());
                    if (Dialogs.getLineDialog() != null) {
                        DrawLineEvent current = Dialogs.getLineDialog().getEvent();
                        current.setP1(click);
                        current.setP2(point);
                        EventBus.getInstance().fireEvent(current);
                    }
                }
            }
            if (Dialogs.getFillDialog() != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    drawClick(event, Color.RED);
                    Dialogs.getFillDialog().setPoint(new Point2((int)event.getX(), (int)event.getY()));
                    Dialogs.getFillDialog().addPoint(new Point2((int)event.getX(), (int)event.getY()));
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    Dialogs.getFillDialog().setPoint(new Point2((int)event.getX(), (int)event.getY()));
                }
            }
            if (Dialogs.getAmputationDialog() != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    drawClick(event, Color.RED);
                    Dialogs.getAmputationDialog().addClipPoint(new Point2((int) event.getX(), (int) event.getY()));
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    drawClick(event, Color.GREEN);
                    Dialogs.getAmputationDialog().addWindowPoint(new Point2((int) event.getX(), (int) event.getY()));
                }
            }
            if (Dialogs.getConversionDialog() != null) {
                drawClick(event, Color.RED);
                Point2 point = new Point2((int) event.getX(), (int) event.getY());
                if (prev != null) {
                    drawLine(prev, point, image);
                    redraw();
                }
                Dialogs.getConversionDialog().addVertex(point);
                prev = point;
            }
        });
    }

    public void redraw() {
        if (image != null)
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    Color color = image.getColor(i, j);
                    getGraphicsContext2D().getPixelWriter().setColor(i, j, color);
                }
            }
    }

    /**
     * Draw and save simple image
     * hack :(
     */
    public void draw(SimpleCanvas simpleCanvas) {
        if (simpleCanvas != null) image = simpleCanvas;
        redraw();
    }

    public void clean() {
        getGraphicsContext2D().clearRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
