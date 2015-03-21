package com.sadvit.dialog;

import com.sadvit.draw.CurveType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class CurveDialogController implements DialogController {

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ComboBox<CurveType> curveTypeBox;

    @FXML
    private Slider brushSizeSlider;

    @FXML
    private RadioButton circleFormButton;

    @FXML
    private RadioButton squareFormButton;

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
        curveTypeBox.setItems(FXCollections.observableArrayList(CurveType.values()));
        curveTypeBox.setValue(CurveType.BEZIER);
        circleFormButton.setSelected(true);
        colorPicker.setValue(Color.BLACK);
    }

}
