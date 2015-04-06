package com.sadvit.dialog;

import com.sadvit.math.Point2;

public class CurveDialog extends AbstractDialog<CurveDialogController> {

    public CurveDialog() {
        super("CurveDialogView.fxml", "Curve", 215, 200);
    }

    public void addPoint(double x, double y) {
        getController().addPoint(new Point2(x, y));
    }

}
