package com.sadvit.image;

public class BitmapInfo {

    public int headerSize;
    public int width;
    public int height;
    public int planes;
    public int bitsPerPixel;
    public int compression;
    public int sizeImage;
    public int horizontalResolution;
    public int verticalResolution;
    public int colorsUsed;
    public int colorsImportant;

    public int getHeaderSize() {
        return headerSize;
    }

    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPlanes() {
        return planes;
    }

    public void setPlanes(int planes) {
        this.planes = planes;
    }

    public int getBitsPerPixel() {
        return bitsPerPixel;
    }

    public void setBitsPerPixel(int bitsPerPixel) {
        this.bitsPerPixel = bitsPerPixel;
    }

    public int getCompression() {
        return compression;
    }

    public void setCompression(int compression) {
        this.compression = compression;
    }

    public int getHorizontalResolution() {
        return horizontalResolution;
    }

    public void setHorizontalResolution(int horizontalResolution) {
        this.horizontalResolution = horizontalResolution;
    }

    public int getVerticalResolution() {
        return verticalResolution;
    }

    public void setVerticalResolution(int verticalResolution) {
        this.verticalResolution = verticalResolution;
    }

    public int getColorsUsed() {
        return colorsUsed;
    }

    public void setColorsUsed(int colorsUsed) {
        this.colorsUsed = colorsUsed;
    }

    public int getColorsImportant() {
        return colorsImportant;
    }

    public void setColorsImportant(int colorsImportant) {
        this.colorsImportant = colorsImportant;
    }

    public int getSizeImage() {
        return sizeImage;
    }

    public void setSizeImage(int sizeImage) {
        this.sizeImage = sizeImage;
    }

    public BitmapInfo() {

    }

}