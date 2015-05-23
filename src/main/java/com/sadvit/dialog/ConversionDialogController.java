package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.event.DrawConversionEvent;
import com.sadvit.math.Matrix;
import com.sadvit.math.Point2;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import org.apache.commons.math3.linear.RealMatrix;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ConversionDialogController implements DialogController {

    @FXML
    private Button cleanButton;

    @FXML
    private Button addScaleButton;

    @FXML
    private Slider scaleDySlider;

    @FXML
    private Slider scaleDxSlider;

    @FXML
    private Slider reflectCSlider;

    @FXML
    private Button addRotateButton;

    @FXML
    private Slider rotateFSlider;

    @FXML
    private Button addReflectButton;

    @FXML
    private Slider reflectASlider;

    @FXML
    private Slider reflectBSlider;

    private RealMatrix matrix = Matrix.getE();

    @FXML
    private Button applyButton;

    @FXML
    private GridPane root;

    @FXML
    private Button addMoveButton;

    @FXML
    private Slider moveDySlider;

    @FXML
    private Slider moveDxSlider;

    @FXML
    private Button cancelButton;

    @FXML
    private TabPane tabPane;

    @Override
    public Button getButtonDraw() {
        return applyButton;
    }

    @Override
    public Button getButtonCancel() {
        return cancelButton;
    }

    @Override
    public GridPane getRoot() {
        return root;
    }

    private LinkedList<Point2> points = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addMoveButton.setOnAction(action -> {
            double dx = moveDxSlider.getValue() + 1.0;
            double dy = moveDySlider.getValue() + 1.0;
            addMove(dx, dy);
        });
        addRotateButton.setOnAction(action -> {
            double f = rotateFSlider.getValue() + 1.0;
            addRotate(f);
        });
        addScaleButton.setOnAction(action -> {
            double dx = scaleDxSlider.getValue() + 1.0;
            double dy = scaleDySlider.getValue() + 1.0;
            addScale(dx, dy);
        });
        addReflectButton.setOnAction(action -> {
            double a = reflectASlider.getValue() + 1.0;
            double b = reflectBSlider.getValue() + 1.0;
            double c = reflectCSlider.getValue() + 1.0;
            addReflect(a, b, c);
        });
        cleanButton.setOnAction(action -> {
            points.clear();
            matrix = Matrix.getE();
        });
    }

    private void addMove(double dx, double dy) {
        matrix = matrix.multiply(Matrix.getMove(dx, dy));
    }

    private void addRotate(double f) {
        matrix = matrix.multiply(Matrix.getRotate(f));
    }

    private void addScale(double dx, double dy) {
        matrix = matrix.multiply(Matrix.getScale(dx, dy));
    }

    private void addReflect(double a, double b, double c) {
        matrix = matrix.multiply(Matrix.getReflect(a, b, c));
    }

    public void draw() {
        EventBus.getInstance().fireEvent(getEvent());
    }

    private DrawConversionEvent getEvent() {
        DrawConversionEvent event = new DrawConversionEvent();
        event.setMatrix(matrix);
        event.setPoints(points);
        return event;
    }

    public void addVertex(Point2 point) {
        points.add(point);
    }

}
