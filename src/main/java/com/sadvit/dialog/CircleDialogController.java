package com.sadvit.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CircleDialogController implements DialogController {

    @FXML
    private Button buttonDraw;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane root;

    @Override
    public Button getButtonDraw() {
        return buttonDraw;
    }

    @Override
    public Button getButtonCancel() {
        return buttonCancel;
    }

    public GridPane getRoot() {
        return root;
    }

    public void draw() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
