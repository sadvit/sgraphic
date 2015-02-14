package com.sadvit.io.writer;


import com.sadvit.image.ImageBMP;
import com.sadvit.io.stream.DataOutputStreamLittleEndian;

import java.io.IOException;

public class ImageBMPWriter {

    private DataOutputStreamLittleEndian stream;

    public ImageBMPWriter(DataOutputStreamLittleEndian stream) {
        this.stream = stream;
    }

    public void write(ImageBMP image) throws IOException {
        try {
            new BitmapFileHeaderWriter(stream).write(image.getFileHeader());
            new BitmapInfoWriter(stream).write(image.getInfoHeader());
            stream.write(image.getPixelData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
