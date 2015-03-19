package com.sadvit.draw.brush;

import com.sadvit.draw.color.PaintBucket;

public class BrushFactory {

    public static Brush getBrush(BrushType brushType, PaintBucket paintBucket, int size) {
        switch (brushType) {
            case CIRCLE:
                if (size == 1) {
                    return new DotBrush(paintBucket, size);
                }
                if (size > 1) {
                    return new CircleBrush(paintBucket, size);
                }
                return null;
            case SQUARE:
                return new SquareBrush(paintBucket, size);
            default:
                return null;
        }
    }

}
