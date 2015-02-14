package com.sadvit.io.writer;


import com.sadvit.image.BitmapInfo;
import com.sadvit.io.stream.DataOutputStreamLittleEndian;

public class BitmapInfoWriter {

    private DataOutputStreamLittleEndian stream;

    public BitmapInfoWriter(DataOutputStreamLittleEndian writer) {
        this.stream = writer;
    }

    public void write(BitmapInfo bitmapInfo) {
        try {
            stream.writeInt32(bitmapInfo.getHeaderSize());
            stream.writeInt32(bitmapInfo.getWidth());
            stream.writeInt32(bitmapInfo.getHeight());

            stream.writeInt16(bitmapInfo.getPlanes());
            stream.writeInt16(bitmapInfo.getBitsPerPixel());
            stream.writeInt32(bitmapInfo.getCompression());
            stream.writeInt32(bitmapInfo.getSizeImage());

            stream.writeInt32(bitmapInfo.getHorizontalResolution());
            stream.writeInt32(bitmapInfo.getVerticalResolution());

            stream.writeInt32(bitmapInfo.getColorsUsed());
            stream.writeInt32(bitmapInfo.getColorsImportant());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
