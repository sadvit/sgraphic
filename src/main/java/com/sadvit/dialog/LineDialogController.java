package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.draw.BoundType;
import com.sadvit.draw.SmoothType;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.draw.template.TrafaretType;
import com.sadvit.draw.MethodType;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.math.Point2;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class LineDialogController implements DialogController {

    @FXML
    private ComboBox<SmoothType> smoothLineBox;

    @FXML
    private Slider brushSizeSlider;

    @FXML
    private RadioButton circleFormButton;

    @FXML
    private RadioButton squareFormButton;

    @FXML
    private RadioButton bound4xRadio;

    @FXML
    private RadioButton bound8xRadio;

    @FXML
    private ComboBox<TrafaretType> typeLineBox;

    @FXML
    private TextField p1xTextField;

    @FXML
    private TextField p1yTextField;

    @FXML
    private TextField p2xTextField;

    @FXML
    private TextField p2yTextField;

    @FXML
    private ColorPicker colorPickerStart;

    @FXML
    private ColorPicker colorPickerEnd;

    @FXML
    private RadioButton methodBresenham;

    @FXML
    private RadioButton methodParametric;

    @FXML
    private Button buttonDraw;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane root;

    public GridPane getRoot() {
        return root;
    }

    @Override
    public Button getButtonDraw() {
        return buttonDraw;
    }

    @Override
    public Button getButtonCancel() {
        return buttonCancel;
    }

    public void draw() {
        EventBus.getInstance().fireEvent(getEvent());
    }

    public DrawLineEvent getEvent() {
        DrawLineEvent event = new DrawLineEvent();
        if (methodBresenham.isSelected()) event.setMethodType(MethodType.BRESENHAM);
        if (methodParametric.isSelected()) event.setMethodType(MethodType.PARAMETRIC);
        event.setP1(getStartPoint());
        event.setP2(getEndPoint());
        Color colorStart = colorPickerStart.getValue();
        Color colorEnd = colorPickerEnd.getValue();
        event.setColorStart(colorStart);
        event.setColorEnd(colorEnd);
        event.setTrafaretType(typeLineBox.getValue()); // TODO not null
        if (bound4xRadio.isSelected()) event.setBoundType(BoundType.X4);
        if (bound8xRadio.isSelected()) event.setBoundType(BoundType.X8);
        if (squareFormButton.isSelected()) event.setBrushType(BrushType.SQUARE);
        if (circleFormButton.isSelected()) event.setBrushType(BrushType.CIRCLE);
        event.setBrushSize((int)brushSizeSlider.getValue());
        event.setSmooth(smoothLineBox.getValue());
        return event;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addNumericChecker(p1xTextField);
        addNumericChecker(p1yTextField);
        addNumericChecker(p2xTextField);
        addNumericChecker(p2yTextField);
        typeLineBox.setItems(FXCollections.observableArrayList(
                TrafaretType.DASH_DOTTED,
                TrafaretType.DASHED,
                TrafaretType.SOLID
        ));
        smoothLineBox.setItems(FXCollections.observableArrayList(
                SmoothType.BR_SMOOTH,
                SmoothType.MASK_SMOOTH,
                SmoothType.WU_SMOOTH
        ));
        typeLineBox.setValue(TrafaretType.SOLID);
        setStartPoint(new Point2(100, 100));
        setEndPoint(new Point2(290, 200));
        methodBresenham.setSelected(true);
        colorPickerStart.setValue(Color.BLACK);
        colorPickerEnd.setValue(Color.BLACK);
        brushSizeSlider.setValue(20);
        bound4xRadio.setSelected(true);
        squareFormButton.setSelected(true);
    }

    private void addNumericChecker(final TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                    textField.setText(Integer.toString(0));
                    return;
                }
                if (!newValue.matches("\\d*") || !positive(newValue))
                    textField.setText(oldValue);
            }

            private boolean positive(String value) {
                try {
                    return Integer.parseInt(value) >= 0;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });
    }

    private int getInteger(TextField textField) {
        return Integer.parseInt(textField.getText());
    }

    private Point2 getPoint(TextField xField, TextField yField) {
        return new Point2(getInteger(xField), getInteger(yField));
    }

    private Point2 getStartPoint() {
        return getPoint(p1xTextField, p1yTextField);
    }

    private Point2 getEndPoint() {
        return getPoint(p2xTextField, p2yTextField);
    }

    private void setStartPoint(Point2 point) {
        p1xTextField.setText(Integer.toString(point.getX()));
        p1yTextField.setText(Integer.toString(point.getY()));
    }

    private void setEndPoint(Point2 point) {
        p2xTextField.setText(Integer.toString(point.getX()));
        p2yTextField.setText(Integer.toString(point.getY()));
    }

}
