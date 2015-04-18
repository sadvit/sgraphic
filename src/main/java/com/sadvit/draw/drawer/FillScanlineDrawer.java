package com.sadvit.draw.drawer;

import com.sadvit.event.DrawFillEvent;
import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;
import javafx.scene.paint.Color;

public class FillScanlineDrawer implements Drawer {

    private Color outlineColor;

    private Color beginInlineColor;

    private Color endInlineColor;

    private Point2 point;

    int maxStackSize = 500;
    int[] xstack = new int[maxStackSize];
    int[] ystack = new int[maxStackSize];
    int stackSize;
    int max;

    public FillScanlineDrawer(DrawFillEvent event) {
        this.point = event.getPoint();
        this.outlineColor = event.getColorOutline();
        this.beginInlineColor = event.getColorInlineStart();
        this.endInlineColor = event.getColorInlineEnd();
    }

    public FillScanlineDrawer(Point2 point) {
        this.point = point;
    }

    @Override
    public void draw(SimpleCanvas canvas) {
        int x = point.getX();
        int y = point.getY();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Color color = canvas.getColor(x, y);
        fillLine(canvas, x, x, y);
        Color newColor = canvas.getColor(x, y);
        canvas.setColor(x, y, color);
        if (color.equals(newColor)) return;
        stackSize = 0;
        push(x, y);
        while (true) {
            x = popx();
            if (x == -1)
                return;
            y = popy();
            if (!canvas.getColor(x, y).equals(color))
                continue;
            int x1 = x;
            int x2 = x;
            while (canvas.getColor(x1, y).equals(color) && x1 >= 0) x1--; // find start of scan-line
            x1++;
            while (canvas.getColor(x2, y).equals(color) && x2 < width) x2++;  // find end of scan-line
            x2--;
            fillLine(canvas, x1, x2, y); // fill scan-line
            boolean inScanLine = false;
            for (int i = x1; i <= x2; i++) { // find scan-lines above this one
                if (!inScanLine && y > 0 && canvas.getColor(i, y - 1).equals(color)) {
                    push(i, y - 1);
                    inScanLine = true;
                } else if (inScanLine && y > 0 && !canvas.getColor(i, y - 1).equals(color))
                    inScanLine = false;
            }
            inScanLine = false;
            for (int i = x1; i <= x2; i++) { // find scan-lines below this one
                if (!inScanLine && y < height - 1 && canvas.getColor(i, y + 1).equals(color)) {
                    push(i, y + 1);
                    inScanLine = true;
                } else if (inScanLine && y < height - 1 && !canvas.getColor(i, y + 1).equals(color))
                    inScanLine = false;
            }
        }
    }

    final void push(int x, int y) {
        stackSize++;
        if (stackSize == maxStackSize) {
            int[] newXStack = new int[maxStackSize * 2];
            int[] newYStack = new int[maxStackSize * 2];
            System.arraycopy(xstack, 0, newXStack, 0, maxStackSize);
            System.arraycopy(ystack, 0, newYStack, 0, maxStackSize);
            xstack = newXStack;
            ystack = newYStack;
            maxStackSize *= 2;
        }
        xstack[stackSize - 1] = x;
        ystack[stackSize - 1] = y;
    }

    final int popx() {
        if (stackSize == 0)
            return -1;
        else
            return xstack[stackSize - 1];
    }

    final int popy() {
        int value = ystack[stackSize - 1];
        stackSize--;
        return value;
    }

    final void fillLine(SimpleCanvas canvas, int x1, int x2, int y) {
        if (x1 > x2) {
            int t = x1;
            x1 = x2;
            x2 = t;
        }
        for (int x = x1; x <= x2; x++)
            canvas.setColor(x, y, Color.BLACK);
    }

}
