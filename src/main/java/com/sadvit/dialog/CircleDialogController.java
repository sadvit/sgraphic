package com.sadvit.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CircleDialogController implements DialogController {

    @FXML
    private Button buttonDraw;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane root;

    @Override
    public Button getButtonDraw() {
        return null;
    }

    @Override
    public Button getButtonCancel() {
        return null;
    }

    public GridPane getRoot() {
        return root;
    }

}
