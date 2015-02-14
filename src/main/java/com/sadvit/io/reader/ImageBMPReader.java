package com.sadvit.io.reader;

import com.sadvit.image.BitmapFileHeader;
import com.sadvit.image.BitmapInfo;
import com.sadvit.image.ImageBMP;
import com.sadvit.io.stream.DataInputStreamLittleEndian;

import java.io.IOException;

public class ImageBMPReader {

    private DataInputStreamLittleEndian stream;

    public ImageBMPReader(DataInputStreamLittleEndian stream) {
        this.stream = stream;
    }

    public ImageBMP read() throws IOException {
        ImageBMP image = new ImageBMP();
            BitmapFileHeader fileHeader = new BitmapFileHeaderReader(stream).read();
            BitmapInfo infoHeader = new BitmapInfoReader(stream).read();
            byte[] pixelData = readPixelData(infoHeader);

            image.setFileHeader(fileHeader);
            image.setInfoHeader(infoHeader);
            image.setPixelData(pixelData);
        return image;
    }

    private byte[] readPixelData(BitmapInfo info) {
        int bytesPerPixel = info.getBitsPerPixel() / 8;
        byte[] data = new byte[((info.getWidth() * bytesPerPixel + 3) / 4 * 4) * info.getHeight()];
        try {
            stream.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}