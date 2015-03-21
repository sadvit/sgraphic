package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.draw.FigureType;
import com.sadvit.draw.MethodType;
import com.sadvit.draw.brush.BrushType;
import com.sadvit.event.DrawCircleEvent;
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

public class CircleDialogController implements DialogController {

    @FXML
    private Slider brushSizeSlider;

    @FXML
    private RadioButton squareFormButton;

    @FXML
    private RadioButton circleFormButton;
/*
    @FXML
    private RadioButton bound8xRadio;

    @FXML
    private RadioButton bound4xRadio;
*/
    @FXML
    private ColorPicker figureColor;

    @FXML
    private RadioButton methodBresenham;

    @FXML
    private RadioButton methodParametric;

    @FXML
    private TextField rTextField;

    @FXML
    private ComboBox<FigureType> figureBox;

    @FXML
    private TextField yTextField;

    @FXML
    private TextField xTextField;

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

    public DrawCircleEvent getEvent() {
        DrawCircleEvent event = new DrawCircleEvent();
        if (methodBresenham.isSelected()) event.setMethodType(MethodType.BRESENHAM);
        if (methodParametric.isSelected()) event.setMethodType(MethodType.PARAMETRIC);
        event.setCoordinates(getCoordinates());
        event.setColor(figureColor.getValue());
        event.setRadius(Integer.parseInt(rTextField.getText()));
        event.setBrushSize((int)(brushSizeSlider.getValue()));
        //if (bound4xRadio.isSelected()) event.setBoundType(BoundType.X4);
        //if (bound8xRadio.isSelected()) event.setBoundType(BoundType.X8);
        if (squareFormButton.isSelected()) event.setBrushType(BrushType.SQUARE);
        if (circleFormButton.isSelected()) event.setBrushType(BrushType.CIRCLE);
        event.setFigureType(figureBox.getValue());
        return event;
    }

    public void draw() {
        System.out.println("FIRE!");
        EventBus.getInstance().fireEvent(getEvent());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addNumericChecker(xTextField);
        addNumericChecker(yTextField);
        addNumericChecker(rTextField);
        figureBox.setItems(FXCollections.observableArrayList(
                FigureType.CIRCLE,
                FigureType.ELLIPSE
        ));
        figureBox.setValue(FigureType.CIRCLE);
        setCoordinates(new Point2(10, 10));
        rTextField.setText(Integer.toString(10));
        methodParametric.setSelected(true);
        figureColor.setValue(Color.BLACK);
        //bound8xRadio.setSelected(true);
        circleFormButton.setSelected(true);
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

    private Point2 getCoordinates() {
        return getPoint(xTextField, yTextField);
    }

    private void setCoordinates(Point2 point) {
        yTextField.setText(Integer.toString(point.getX()));
        xTextField.setText(Integer.toString(point.getY()));
    }

}
