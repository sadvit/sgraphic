package com.sadvit.dialog;


import javafx.scene.Node;

public class CircleDialog extends AbstractDialog<CircleDialogController> {

    public CircleDialog() {
        super("CircleDialogView.fxml", CircleDialogController.class, "Circle", 300, 600);
    }

    @Override
    public Node getRootView() {
        return getController().getRoot();
    }

    @Override
    protected void initialize() {

    }

}
