package com.sadvit.dialog;


import com.sadvit.event.DrawLineEvent;

public class LineDialog extends AbstractDialog<LineDialogController> {

    public LineDialog() {
        super("LineDialogView.fxml", "Line", 280, 450);
    }

    public DrawLineEvent getEvent() {
        return getController().getEvent();
    }

}
