package com.sadvit.io.reader;

import com.sadvit.image.BitmapInfo;
import com.sadvit.io.stream.DataInputStreamLittleEndian;

import java.io.IOException;

// 40 bytes
public class BitmapInfoReader {

    private DataInputStreamLittleEndian reader;

    public BitmapInfoReader(DataInputStreamLittleEndian reader) {
        this.reader = reader;
    }

    public BitmapInfo read() {
        BitmapInfo bitmapInfo = new BitmapInfo();
        try {
            bitmapInfo.setHeaderSize(reader.readInt32());
            bitmapInfo.setWidth(reader.readInt32());
            bitmapInfo.setHeight(reader.readInt32());
            bitmapInfo.setPlanes(reader.readInt16());
            bitmapInfo.setBitsPerPixel(reader.readInt16());
            bitmapInfo.setCompression(reader.readInt32());
            bitmapInfo.setSizeImage(reader.readInt32());
            bitmapInfo.setHorizontalResolution(reader.readInt32());
            bitmapInfo.setVerticalResolution(reader.readInt32());
            bitmapInfo.setColorsUsed(reader.readInt32());
            bitmapInfo.setColorsImportant(reader.readInt32());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapInfo;
    }

}
