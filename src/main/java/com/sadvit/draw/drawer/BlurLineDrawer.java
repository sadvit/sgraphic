package com.sadvit.draw.drawer;

import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.ImageBMP;
import com.sadvit.image.SimpleCanvas;
import javafx.scene.paint.Color;

public class BlurLineDrawer extends LineDrawer {

    private DrawLineEvent event;

    public BlurLineDrawer(DrawLineEvent event) {
        super(event);
        this.event = event;
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        new Line8BrezenhamDrawer(event).draw(canvas);
        ImageBMP image = (ImageBMP) canvas;
        int yMin = 1, yMax = canvas.getHeight() - 2, xMin = 1, xMax = canvas.getWidth() - 2, width = canvas.getWidth();
        Color diff;
        byte[] array = image.getPixelData();

        byte[] pixels2 = new byte[array.length];
        System.arraycopy(array, 0, pixels2, 0, array.length);

        int offset;
        for (int y = yMin; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                Color c1 = image.getColor(x - 1, y - 1);
                Color c2 = image.getColor(x + 1, y + 1);
                Color c3 = image.getColor(x - 1, y + 1);
                Color c4 = image.getColor(x + 1, y - 1);
                Color c5 = image.getColor(x, y);
                Color c6 = image.getColor(x, y - 1);
                Color c7 = image.getColor(x, y + 1);
                Color c8 = image.getColor(x - 1, y);
                Color c9 = image.getColor(x + 1, y);
                diff = diff(c1, c2, c3, c4, c5, c6, c7, c8, c9);
                image.setColor(x, y, diff);
            }
        }
    }

    private Color diff(Color... colors) {
        double rs = 0, gs = 0, bs = 0;
        for (Color color : colors) {
            rs += color.getRed();
            gs += color.getGreen();
            bs += color.getBlue();
        }
        double drs = rs / 9.0, dgs = gs / 9.0, dbs = bs / 9.0;
        return  Color.color(drs, dgs, dbs);
    }

}