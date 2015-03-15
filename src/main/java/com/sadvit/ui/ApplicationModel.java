package com.sadvit.ui;

import com.sadvit.draw.Brush;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleImage;
import com.sadvit.image.SimpleImageUtils;
import com.sadvit.math.Point;
import com.sadvit.mvc.AbstractModel;
import javafx.scene.paint.Color;

public class ApplicationModel extends AbstractModel {

    private SimpleImage currentImage;

    public SimpleImage getCurrentImage() {
        return currentImage;
    }

    public void openImage(String path) {
        currentImage = SimpleImageUtils.read(path);
    }

    public void saveImage(String path) {
        if (currentImage != null)
            SimpleImageUtils.write(currentImage, path);
    }

    public void createWhiteImage(int width, int height) {
        currentImage = SimpleImageUtils.create(width, height);
    }

    public void createMosaicImage(int width, int height, int size) {
        currentImage = SimpleImageUtils.create(width, height);
        for (int i = 0; i < currentImage.getWidth() - 1; i += size) {
            for (int j = 0; j < currentImage.getHeight() - 1; j += size) {
                Color c = randomColor();
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        currentImage.setColor(i + k, j + l, c);
                    }
                }
            }
        }
    }

    public void createLine(DrawLineEvent event) {
        double x1 = event.getP1().getX();
        double y1 = event.getP1().getY();
        double x2 = event.getP2().getX();
        double y2 = event.getP2().getY();
        Color v1 = event.getColorStart();
        Color v2 = event.getColorEnd();
        double N = Math.max(Math.abs(x2-x1), Math.abs(y2-y1));
        double t = 1.0 / (N - 1.0);

        double dx = (x2 - x1) / N;
        double dy = (y2 - y1) / N;
        double dt = t / N;

        double xi = x1;
        double yi = y1;
        double ti = 0;

        for (int i = 0; i < N; i++) {
            xi += dx;
            yi += dy;
            ti += dt;
            Point point = new Point((int)xi, (int)yi);
            Color color = v1.interpolate(v2, ti);
            Brush.square(currentImage, point, color);
        }

        System.out.println(event);
    }

    private Color randomColor() {
        return new Color(Math.random(), Math.random(), Math.random(), 1);
    }

    public void updateSize(int width, int height) {
        // TODO impl update size in utils
    }

}
