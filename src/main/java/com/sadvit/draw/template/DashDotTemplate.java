package com.sadvit.draw.template;

import com.sadvit.draw.brush.Brush;

// TODO ПЕРЕДЕЛАТЬ ЭТОТ ТРАФАРЕТ И ВООБЩЕ ПЕРЕСМОТРЕТЬ РЕАЛИЗАЦИЮ ВСЕХ ТРАФАРЕТОВ.
public class DashDotTemplate extends BrushTemplate {

    private int countDown;

    private int countUp;

    private int length = getSize() * 5;

    private boolean isDown = true;

    private boolean isPoint;

    public DashDotTemplate(Brush brush) {
        super(brush);
    }

    @Override
    protected boolean isNextEnabled() {
        if (isDown) {
            if (isPoint) {
                isPoint = false;
                return true;
            }
            // кисть опущена
            // 1. Расстояние не превышено -> увеличиваем счетчик, возвращаем текущее значение isDown
            // 2. Превышено -> обнуляем счетчик, меняем состояние isDown и возвращаем
            if (countDown < length) {
                countDown++;
            } else {
                countDown = 0;
                isDown = false;
                isPoint = true;
            }
        } else {
            // кисть поднята
            // 1. Расстояние не превышено -> увеличиваем счетчик, возвращаем текущее значение isDown
            // 2. Превышено -> обнуляем счетчик, меняем состояние isDown и возвращаем
            if (countUp < 1) {
                countUp++;
            } else {
                countUp = 0;
                isDown = true;
            }
        }
        return !isDown;
    }

}