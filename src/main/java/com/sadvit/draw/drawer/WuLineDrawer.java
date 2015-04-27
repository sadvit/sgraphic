package com.sadvit.draw.drawer;

import com.sadvit.event.DrawLineEvent;
import com.sadvit.image.SimpleCanvas;
import javafx.scene.paint.Color;

public class WuLineDrawer extends LineDrawer {

    public WuLineDrawer(DrawLineEvent event) {
        super(event);
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        int swap;
        if (steep) {
            swap = x1;
            x1 = y1;
            y1 = swap;
            swap = x2;
            x2 = y2;
            y2 = swap;
        }
        if (x1 > x2) {
            swap = x1;
            x1 = x2;
            x2 = swap;
            swap = y1;
            y1 = y2;
            y2 = swap;
        }
        point(steep, x1, y1, 1, canvas);
        point(steep, x2, y2, 1, canvas);
        float dx = x2 - x1;
        float dy = y2 - y1;
        float gradient = dy / dx;
        float y = y1 + gradient;
        for (int x = x1 + 1; x <= x2 - 1; x++) {
            point(steep, x, (int) y, 1 - (y - (int) y), canvas);
            point(steep, x, (int) y + 1, y - (int) y, canvas);
            y += gradient;
        }
    }

    private void point(boolean steep, int x, int y, float c, SimpleCanvas canvas) {
        if (!steep) {
            Color color = canvas.getColor(x, y);
            double r = color.getRed() * (1 - c);
            double g = color.getGreen() * (1 - c);
            double b = color.getBlue() * (1 - c);
            canvas.setColor(x, y, Color.color(r, g, b));
        } else {
            Color color = canvas.getColor(y, x);
            double r = color.getRed() * (1 - c);
            double g = color.getGreen() * (1 - c);
            double b = color.getBlue() * (1 - c);
            canvas.setColor(y, x, Color.color(r, g, b));
        }
    }

}
