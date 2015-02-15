package com.sadvit.ui;

import com.sadvit.image.SimpleImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class ImageCanvas extends Canvas {

    private SimpleImage image;

    public ImageCanvas() {
        widthProperty().addListener(evt -> redraw());
        heightProperty().addListener(evt -> redraw());
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
