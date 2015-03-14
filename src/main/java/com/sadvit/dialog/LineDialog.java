package com.sadvit.dialog;


import javafx.scene.Node;

public class LineDialog extends AbstractDialog<LineDialogController> {

    public LineDialog() {
        super("LineDialogView.fxml", LineDialogController.class, "Line", 300, 600);
    }

    @Override
    public Node getRootView() {
        return getController().getRoot();
    }

    @Override
    protected void initialize() {

    }

}
