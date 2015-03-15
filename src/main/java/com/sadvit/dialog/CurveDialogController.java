package com.sadvit.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CurveDialogController implements DialogController {

    @FXML
    private GridPane root;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonDraw;

    @Override
    public Button getButtonDraw() {
        return buttonDraw;
    }

    @Override
    public Button getButtonCancel() {
        return buttonCancel;
    }

    @Override
    public GridPane getRoot() {
        return root;
    }

    public void draw() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
