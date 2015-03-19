package com.sadvit.draw.color;

import javafx.scene.paint.Color;

/**
 * Ведро с краскми. Следующий цвет может быть другим в зависимости от реализации
 */
public interface PaintBucket {

    public Color getNextColor();

}
