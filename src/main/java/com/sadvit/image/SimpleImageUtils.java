package com.sadvit.image;

import com.sadvit.io.reader.ImageBMPReader;
import com.sadvit.io.stream.DataInputStreamLittleEndian;
import com.sadvit.io.stream.DataOutputStreamLittleEndian;
import com.sadvit.io.writer.ImageBMPWriter;
import javafx.scene.paint.Color;

import java.io.IOException;

public class SimpleImageUtils {

    public static SimpleImage read(String path) {
        try {
            DataInputStreamLittleEndian stream = new DataInputStreamLittleEndian(path);
            ImageBMP imageBMP = new ImageBMPReader(stream).read();
            stream.close();
            return imageBMP;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(SimpleImage simpleImage, String path) {
        if (simpleImage instanceof ImageBMP) {
            try {
                ImageBMP image = (ImageBMP) simpleImage;
                DataOutputStreamLittleEndian stream = new DataOutputStreamLittleEndian(path);
                new ImageBMPWriter(stream).write(image);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Format not supported");
        }
    }

    /**
     * Create new SimpleImage instance, based on ImageBMP class with white background
     */
    public static SimpleImage create(int width, int height) {
        ImageBMP imageBMP = new ImageBMP();
        BitmapFileHeader bitmapFileHeader = new BitmapFileHeader();
        bitmapFileHeader.setSignature("BM");
        bitmapFileHeader.setImageDataOffset(54);
        BitmapInfo bitmapInfo = new BitmapInfo();
        bitmapInfo.setHeaderSize(40);
        bitmapInfo.setWidth(width);
        bitmapInfo.setHeight(height);
        bitmapInfo.setPlanes(1);
        bitmapInfo.setBitsPerPixel(24);
        bitmapInfo.setHorizontalResolution(width);
        bitmapInfo.setVerticalResolution(height);
        imageBMP.setFileHeader(bitmapFileHeader);
        imageBMP.setInfoHeader(bitmapInfo);
        byte[] data = new byte[((width * 3 + 3) / 4 * 4) * height];
        bitmapFileHeader.setFileSize(data.length + 54);
        bitmapInfo.setSizeImage(data.length);
        imageBMP.setPixelData(data);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                imageBMP.setColor(i, j, Color.WHITE);
            }
        }
        return imageBMP;
    }

}
