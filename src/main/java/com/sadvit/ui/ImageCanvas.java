package com.sadvit.ui;

import com.sadvit.image.SimpleImage;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class ImageCanvas extends Canvas {

    private SimpleImage image;

    public void setImage(SimpleImage image) {
        this.image = image;
    }

    public SimpleImage getImage() {
        return image;
    }

    public ImageCanvas() {
        widthProperty().addListener(evt -> refresh());
        heightProperty().addListener(evt -> refresh());
    }

    public void refresh() {
        if (image != null) {
            draw(image);
        }
    }

    private void draw(SimpleImage simpleImage) {
        for (int i = 0; i < simpleImage.getWidth(); i++) {
            for (int j = 0; j < simpleImage.getHeight(); j++) {
                Color color = simpleImage.getColor(i, j);
                getGraphicsContext2D().getPixelWriter().setColor(i, j, color);
            }
        }
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
