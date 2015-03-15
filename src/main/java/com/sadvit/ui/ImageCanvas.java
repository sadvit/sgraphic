package com.sadvit.ui;

import com.sadvit.communication.EventBus;
import com.sadvit.dialog.Dialogs;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleImage;
import com.sadvit.math.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ImageCanvas extends Canvas {

    private SimpleImage image;

    private Point click;

    private boolean firstClick = true;

    public ImageCanvas() {
        widthProperty().addListener(evt -> redraw());
        heightProperty().addListener(evt -> redraw());
        addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (firstClick) {
                firstClick = false;
                click = new Point((int)event.getX(), (int)event.getY());
            } else {
                firstClick = true;
                Point point = new Point((int)event.getX(), (int)event.getY());
                if (Dialogs.getLineDialog() != null) {
                    DrawLineEvent current = Dialogs.getLineDialog().getEvent();
                    current.setP1(click);
                    current.setP2(point);
                    EventBus.getInstance().fireEvent(current);
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
    public void draw(SimpleImage simpleImage) {
        if (simpleImage != null) image = simpleImage;
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
