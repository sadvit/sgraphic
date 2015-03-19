package com.sadvit.draw.template;

import com.sadvit.draw.brush.Brush;

public class DashTemplate extends BrushTemplate {

    private int countDown;

    private int countUp;

    private int length = getSize() * 5;

    private boolean isDown = true;

    @Override
    protected boolean isNextEnabled() {
        if (isDown) {
            // кисть опущена
            // 1. Расстояние не превышено -> увеличиваем счетчик, возвращаем текущее значение isDown
            // 2. Превышено -> обнуляем счетчик, меняем состояние isDown и возвращаем
            if (countDown < length) {
                countDown++;
            } else {
                countDown = 0;
                isDown = false;
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

    public DashTemplate(Brush brush) {
        super(brush);
    }

}