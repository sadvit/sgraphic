package com.sadvit.ui;

import com.sadvit.communication.EventBus;
import com.sadvit.dialog.Dialogs;
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

    public ImageCanvas() {
        widthProperty().addListener(evt -> redraw());
        heightProperty().addListener(evt -> redraw());
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (Dialogs.getCurveDialog() != null) {
                Dialogs.getCurveDialog().addPoint(event.getX(), event.getY());
                image.setColor(new Point2(event.getX(), event.getY()), Color.RED);
                image.setColor(new Point2(event.getX() - 1, event.getY()), Color.RED);
                image.setColor(new Point2(event.getX() - 1, event.getY() - 1), Color.RED);
                image.setColor(new Point2(event.getX(), event.getY() - 1), Color.RED);
                redraw();
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
                    image.setColor(new Point2(event.getX(), event.getY()), Color.RED);
                    image.setColor(new Point2(event.getX() - 1, event.getY()), Color.RED);
                    image.setColor(new Point2(event.getX() - 1, event.getY() - 1), Color.RED);
                    image.setColor(new Point2(event.getX(), event.getY() - 1), Color.RED);
                    redraw();

                    Dialogs.getFillDialog().setPoint(new Point2((int)event.getX(), (int)event.getY()));
                    Dialogs.getFillDialog().addPoint(new Point2((int)event.getX(), (int)event.getY()));
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    Dialogs.getFillDialog().setPoint(new Point2((int)event.getX(), (int)event.getY()));
                }
            }
            if (Dialogs.getAmputationDialog() != null) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Dialogs.getAmputationDialog().addClipPoint(new Point2((int) event.getX(), (int) event.getY()));
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    Dialogs.getAmputationDialog().addWindowPoint(new Point2((int) event.getX(), (int) event.getY()));
                }
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
