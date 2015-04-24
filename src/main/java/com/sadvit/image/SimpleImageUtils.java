package com.sadvit.image;

import com.sadvit.draw.MethodType;
import com.sadvit.draw.drawer.FillScanlineDrawer;
import com.sadvit.io.reader.ImageBMPReader;
import com.sadvit.io.reader.WavefrontReader;
import com.sadvit.io.stream.DataInputStreamLittleEndian;
import com.sadvit.io.stream.DataOutputStreamLittleEndian;
import com.sadvit.io.writer.ImageBMPWriter;
import com.sadvit.math.Point2;
import com.sadvit.math.PointAdaptor;
import com.sadvit.math.Triangle;
import com.sadvit.ui.ApplicationController;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleImageUtils {

    public static SimpleCanvas readOBJ(String path, MethodType methodType) {
        SimpleCanvas simpleCanvas = create(ApplicationController.INITIAL_WINDOW_SIZE, ApplicationController.INITIAL_WINDOW_SIZE);
        List<Triangle> triangles = WavefrontReader.readTriangles(path);
        long start = System.nanoTime();
        System.out.println("start: " + start);
        for (Triangle triangle : triangles) {
            Point2 p1 = new PointAdaptor(triangle.getP1()).getPoint();
            Point2 p2 = new PointAdaptor(triangle.getP2()).getPoint();
            Point2 p3 = new PointAdaptor(triangle.getP3()).getPoint();

            /*if (methodType.equals(MethodType.BRESENHAM)) {
                new Line8BrezenhamDrawer(DrawLineEvent.simpleEvent(p1, p2, MethodType.BRESENHAM)).draw(simpleCanvas);
                new Line8BrezenhamDrawer(DrawLineEvent.simpleEvent(p2, p3, MethodType.BRESENHAM)).draw(simpleCanvas);
                new Line8BrezenhamDrawer(DrawLineEvent.simpleEvent(p1, p3, MethodType.BRESENHAM)).draw(simpleCanvas);
            }
            if (methodType.equals(MethodType.PARAMETRIC)) {
                new Line8ParametricDrawer(DrawLineEvent.simpleEvent(p1, p2, MethodType.PARAMETRIC)).draw(simpleCanvas);
                new Line8ParametricDrawer(DrawLineEvent.simpleEvent(p2, p3, MethodType.PARAMETRIC)).draw(simpleCanvas);
                new Line8ParametricDrawer(DrawLineEvent.simpleEvent(p1, p3, MethodType.PARAMETRIC)).draw(simpleCanvas);
            }*/

            List<Point2> points = new ArrayList<>();
            points.add(p1);
            points.add(p2);
            points.add(p3);
            new FillScanlineDrawer(points).draw(simpleCanvas);


        }
        System.out.println("diff: " + (System.nanoTime() - start));
        return simpleCanvas;
    }

    public static SimpleCanvas readBMP(String path) {
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

    public static void write(SimpleCanvas simpleCanvas, String path) {
        if (simpleCanvas instanceof ImageBMP) {
            try {
                ImageBMP image = (ImageBMP) simpleCanvas;
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
    public static SimpleCanvas create(int width, int height) {
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
