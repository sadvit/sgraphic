package com.sadvit.draw.brush;

import com.sadvit.image.SimpleCanvas;
import com.sadvit.math.Point2;

/**
 * Представляет собой кисть. Кисть берет цвет у ColorProvider-a, определяет - не пуста ли текущая точка у
 * DotProvider-a и окрашивает изображение касаясь его через метод touch. При этом в зависимости от реализации
 * кисть может быть разной формы и размера.
 */
public interface Brush {

    /**
     * Касание кисти. Реализуется окрашивание места касания
     * @param point точка касания
     * @param canvas холст для рисования
     */
    public void touch(Point2 point, SimpleCanvas canvas);

    public int getSize();

}
