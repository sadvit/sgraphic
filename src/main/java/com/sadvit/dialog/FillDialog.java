package com.sadvit.dialog;

import com.sadvit.math.Point2;

public class FillDialog extends AbstractDialog<FillDialogController> {

    public FillDialog() {
        super("FillDialogView.fxml", "Fill", 270, 200);
    }

    public void setPoint(Point2 point) {
        getController().setPoint(point);
    }

}
