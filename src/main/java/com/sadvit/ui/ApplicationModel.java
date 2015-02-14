package com.sadvit.ui;

import com.sadvit.image.SimpleImage;
import com.sadvit.image.SimpleImageUtils;
import com.sadvit.mvc.AbstractModel;

public class ApplicationModel extends AbstractModel {

    private SimpleImage currentImage;

    public SimpleImage getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(SimpleImage currentImage) {
        this.currentImage = currentImage;
    }

    public void openImage(String path) {
        currentImage = SimpleImageUtils.read(path);
    }

    public void saveImage(String path) {
        if (currentImage != null)
            SimpleImageUtils.write(currentImage, path);
    }

    public void createImage(int width, int height) {
        currentImage = SimpleImageUtils.create(width, height);
    }

    public void updateSize(int width, int height) {
        // TODO impl update size in utils
    }

}
