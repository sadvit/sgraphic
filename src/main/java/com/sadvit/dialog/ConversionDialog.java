package com.sadvit.dialog;

import com.sadvit.math.Point2;

public class ConversionDialog extends AbstractDialog<ConversionDialogController> {

    public ConversionDialog() {
        super("ConversionDialogView.fxml", "Conversion", 415, 300);
    }

    public void addVertex(Point2 point) {
        getController().addVertex(point);
    }

}
