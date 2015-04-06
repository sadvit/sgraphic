package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.draw.CurveType;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.event.DrawCurveEvent;
import com.sadvit.math.Point2;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
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

    private ArrayList<Point2> points = new ArrayList<>();

    public void addPoint(Point2 point) {
        points.add(point);
    }

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
        EventBus.getInstance().fireEvent(getEvent());
    }

    @SuppressWarnings("unchecked")
    public DrawCurveEvent getEvent() {
        if (points != null && points.size() > 1) {
            DrawCurveEvent event = new DrawCurveEvent();
            event.setColor(colorPicker.getValue());
            event.setBrushSize((int) (brushSizeSlider.getValue()));
            if (squareFormButton.isSelected())
                event.setBrushType(BrushType.SQUARE);
            if (circleFormButton.isSelected())
                event.setBrushType(BrushType.CIRCLE);
            event.setPoints((ArrayList<Point2>) points.clone()); // TODO checke
            event.setCurveType(curveTypeBox.getValue());
            points.clear();

            return event;
        }
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        curveTypeBox.setItems(FXCollections.observableArrayList(CurveType.values()));
        curveTypeBox.setValue(CurveType.BEZIER);
        circleFormButton.setSelected(true);
        colorPicker.setValue(Color.BLACK);
    }

}
