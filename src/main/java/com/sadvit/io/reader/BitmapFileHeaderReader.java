package com.sadvit.io.reader;

import com.sadvit.image.BitmapFileHeader;
import com.sadvit.io.stream.DataInputStreamLittleEndian;

import java.io.IOException;

public class BitmapFileHeaderReader {

    private DataInputStreamLittleEndian reader;

    public BitmapFileHeaderReader(DataInputStreamLittleEndian reader) {
        this.reader = reader;
    }

    public BitmapFileHeader read() {
        BitmapFileHeader bitmapFileHeader = new BitmapFileHeader();
        try {
            bitmapFileHeader.setSignature(reader.readString(2));
            bitmapFileHeader.setFileSize(reader.readInt32());
            reader.skip(4); // reserved
            bitmapFileHeader.setImageDataOffset(reader.readInt32());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmapFileHeader;
    }

}
