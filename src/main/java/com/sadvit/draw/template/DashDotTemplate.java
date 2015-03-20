package com.sadvit.draw.template;

import com.sadvit.draw.brush.Brush;

public class DashDotTemplate extends BrushTemplate {

    public DashDotTemplate(Brush brush) {
        super(brush);
    }

    private boolean bInt = true, sInt, pInt;

    private int n;

    private int sn;

    @Override
    protected boolean isNextEnabled() {

        if (bInt) {
            // большой интервал
            if (n < 20 * getSize()) {
                n++;
                return true;
            } else {
                n = 0;
                bInt = false;
                sInt = true;
            }
        }
        if (sInt) {
            // маленький интервал
            if (n < 5 * getSize()) {
                n++;
                return false;
            } else {
                n = 0;
                if (sn == 1) {
                    sInt = false;
                    bInt = true;
                    sn = 0;
                } else {
                    sInt = false;
                    pInt = true;
                    sn++;
                }
            }
        }
        if (pInt) {
            // одиночная точка
            if (n < getSize()) {
                n++;
                return true;
            } else {
                n = 0;
                pInt = false;
                sInt = true;
            }
        }

        return false;

    }

}