package com.sadvit.ui;

import com.sadvit.draw.drawer.Drawers;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.image.SimpleImageUtils;
import com.sadvit.mvc.AbstractModel;
import javafx.scene.paint.Color;

public class ApplicationModel extends AbstractModel {

    private SimpleCanvas currentImage;

    public SimpleCanvas getCurrentImage() {
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
        Drawers.line(event, currentImage).draw();
    }

    private Color randomColor() {
        return new Color(Math.random(), Math.random(), Math.random(), 1);
    }

    public void updateSize(int width, int height) {
        // TODO impl update size in utils
    }

}
