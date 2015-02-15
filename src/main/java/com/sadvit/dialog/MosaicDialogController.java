package com.sadvit.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MosaicDialogController {

    @FXML
    private GridPane root;

    @FXML
    private TextField input;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonOk;

    public TextField getInput() {
        return input;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Button getButtonOk() {
        return buttonOk;
    }

    public GridPane getRoot() {
        return root;
    }
}
