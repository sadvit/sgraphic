package com.sadvit.image;

import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

public class ImageBMP implements SimpleCanvas {

    public BitmapFileHeader fileHeader;
    public BitmapInfo infoHeader;
    public byte[] pixelData;

    public ImageBMP() {

    }

    public BitmapFileHeader getFileHeader() {
        return fileHeader;
    }

    public void setFileHeader(BitmapFileHeader fileHeader) {
        this.fileHeader = fileHeader;
    }

    public BitmapInfo getInfoHeader() {
        return infoHeader;
    }

    public void setInfoHeader(BitmapInfo infoHeader) {
        this.infoHeader = infoHeader;
    }

    public byte[] getPixelData() {
        return pixelData;
    }

    public void setPixelData(byte[] pixelData) {
        this.pixelData = pixelData;
    }

    @Override
    public Color getColor(int x, int y) {
        int position = position(x, y);
        int R = pixelData[position + 2] & 0xFF;
        int G = pixelData[position + 1] & 0xFF;
        int B = pixelData[position ] & 0xFF;
        return Color.rgb(R, G, B);
    }

    @Override
    public void setColor(int x, int y, Color color) {
        int position = position(x, y);
        pixelData[position] = toByte(color.getBlue());
        pixelData[position + 1] = toByte(color.getGreen());
        pixelData[position + 2] = toByte(color.getRed());
    }

    public Color getValue(int position) {
        int R = pixelData[position + 2] & 0xFF;
        int G = pixelData[position + 1] & 0xFF;
        int B = pixelData[position ] & 0xFF;
        return Color.rgb(R, G, B);
    }

    public void setValue(int position, Color color) {
        pixelData[position] = toByte(color.getBlue());
        pixelData[position + 1] = toByte(color.getGreen());
        pixelData[position + 2] = toByte(color.getRed());
    }

    @Override
    public Color getColor(Point2 point) {
        return getColor(point.getX(), point.getY());
    }

    @Override
    public void setColor(Point2 point, Color color) {
        setColor(point.getX(), point.getY(), color);
    }

    private byte toByte(double component) {
        return (byte)((int)(component * 255.0));
    }

    @Override
    public int getWidth() {
        return infoHeader.getWidth();
    }

    @Override
    public int getHeight() {
        return infoHeader.getHeight();
    }

    @Override
    public void clean() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                setColor(i, j, Color.WHITE);
            }
        }
    }

    /**
     * Calculate position point with coordinates X and Y in pixel byte array
     */
    private int position(int x, int y) {
        y = getHeight() - y - 1; // inverted position
        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
            int bytesPerPixel = infoHeader.getBitsPerPixel() / 8;
            int width = (infoHeader.getWidth() * bytesPerPixel + 3) / 4 * 4;
            return y * width + (x * bytesPerPixel);
        } else {
            return -1;
        }
    }

}