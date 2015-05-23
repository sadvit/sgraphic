package com.sadvit.image;

// 14 bytes
public class BitmapFileHeader {

    public String signature;
    public int fileSize;
    public int imageDataOffset;

    public BitmapFileHeader() {
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getImageDataOffset() {
        return imageDataOffset;
    }

    public void setImageDataOffset(int imageDataOffset) {
        this.imageDataOffset = imageDataOffset;
    }

    @Override
    protected BitmapFileHeader clone() {
        BitmapFileHeader header = new BitmapFileHeader();
        header.setSignature(signature);
        header.setFileSize(fileSize);
        header.setImageDataOffset(imageDataOffset);
        return header;
    }
}