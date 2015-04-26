package com.sadvit.draw.drawer;

import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import javafx.scene.paint.Color;

public class ModBrezenhamDrawer extends LineDrawer {

    public ModBrezenhamDrawer(DrawLineEvent event) {
        super(event);
    }

    void plotLineAA(int x0, int y0, int x1, int y1, SimpleCanvas canvas) {
        int dx = Math.abs(x1 - x0), sx = x0 < x1 ? 1 : -1;
        int dy = Math.abs(y1 - y0), sy = y0 < y1 ? 1 : -1;
        int err = dx - dy, e2, x2;
        double ed = dx + dy == 0 ? 1 : Math.sqrt((float) dx * dx + (float) dy * dy);

        for (; ; ) {
            draw(x0, y0, Math.abs(err - dx + dy) / ed, canvas);
            e2 = err;
            x2 = x0;
            if (2 * e2 >= -dx) {
                if (x0 == x1) break;
                if (e2 + dy < ed) {
                    draw(x0, y0 + sy, (e2 + dy) / ed, canvas);
                }
                err -= dy;
                x0 += sx;
            }
            if (2 * e2 <= dy) {
                if (y0 == y1) break;
                if (dx - e2 < ed) {
                    draw(x2 + sx, y0, (dx - e2) / ed, canvas);
                }
                err += dx;
                y0 += sy;
            }
        }
    }

    private void draw(int x, int y, double c, SimpleCanvas canvas) {
        Color begin = Color.WHITE;
        Color end = intensity(begin, c);
        canvas.setColor(x, y, end);
    }

    private Color intensity(Color color, double c) {
        System.out.println(c);
        double r = color.getRed() * c;
        double g = color.getGreen() * c;
        double b = color.getBlue() * c;
        return Color.color(r, g, b);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        plotLineAA(x1, y1, x2, y2, canvas);
    }

}
