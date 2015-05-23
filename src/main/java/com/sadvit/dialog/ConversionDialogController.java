package com.sadvit.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ConversionDialogController implements DialogController {

    @FXML
    public Button applyButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TabPane tabPane;

    @Override
    public Button getButtonDraw() {
        return null;
    }

    @Override
    public Button getButtonCancel() {
        return null;
    }

    @Override
    public GridPane getRoot() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
