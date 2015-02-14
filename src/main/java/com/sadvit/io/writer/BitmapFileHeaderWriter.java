package com.sadvit.io.writer;


import com.sadvit.image.BitmapFileHeader;
import com.sadvit.io.stream.DataOutputStreamLittleEndian;

import java.io.IOException;

public class BitmapFileHeaderWriter {

    private DataOutputStreamLittleEndian writer;

    public BitmapFileHeaderWriter(DataOutputStreamLittleEndian writer) {
        this.writer = writer;
    }

    public void write(BitmapFileHeader fileHeader) {
        try {
            writer.write(new byte[]{'B', 'M'});
            writer.writeInt32(fileHeader.getFileSize());
            writer.writeInt16(0); // reserved
            writer.writeInt16(0);
            writer.writeInt32(fileHeader.getImageDataOffset());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
