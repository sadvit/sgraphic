package com.sadvit.dialog;

import com.sadvit.math.Point2;

public class AmputationDialog extends AbstractDialog<AmputationDialogController> {

    public AmputationDialog() {
        super("AmputationDialogView.fxml", "Amputation", 215, 200);
    }

    public void addClipPoint(Point2 point) {
        getController().addClipPoint(point);
    }

    public void addWindowPoint(Point2 point) {
        getController().addWindowPoint(point);
    }

}
