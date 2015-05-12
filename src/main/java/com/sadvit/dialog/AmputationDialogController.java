package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.draw.AmputationType;
import com.sadvit.event.DrawWindowEvent;
import com.sadvit.math.Point2;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AmputationDialogController implements DialogController {

    @FXML
    private TextField fieldY1;

    @FXML
    private TextField fieldX0;

    @FXML
    private TextField fieldY0;

    @FXML
    private TextField fieldX1;

    @FXML
    private GridPane root;

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonDraw;

    @FXML
    private ComboBox<AmputationType> methodTypeBox;

    private LinkedList<Point2> clipPoints = new LinkedList<>();
    private LinkedList<Point2> windowPoints = new LinkedList<>();

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

    private void redrawHandler(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            draw();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        methodTypeBox.setItems(FXCollections.observableArrayList(AmputationType.values()));
        methodTypeBox.setValue(AmputationType.COHEN_SUTHERLAND);
        fieldX0.addEventHandler(KeyEvent.KEY_PRESSED, this::redrawHandler);
        fieldY0.addEventHandler(KeyEvent.KEY_PRESSED, this::redrawHandler);
        fieldX1.addEventHandler(KeyEvent.KEY_PRESSED, this::redrawHandler);
        fieldY1.addEventHandler(KeyEvent.KEY_PRESSED, this::redrawHandler);

        fieldX0.setText(String.valueOf(50));
        fieldY0.setText(String.valueOf(50));
        fieldX1.setText(String.valueOf(300));
        fieldY1.setText(String.valueOf(300));
    }

    public void draw() {
        EventBus.getInstance().fireEvent(getEvent());
    }

    public void clean() {
        clipPoints.clear();
        windowPoints.clear();
    }

    public DrawWindowEvent getEvent() {
        DrawWindowEvent event = new DrawWindowEvent();
        event.setType(methodTypeBox.getValue());
        Point2 p1 = new Point2(Integer.valueOf(fieldX0.getText()), Integer.valueOf(fieldY0.getText()));
        Point2 p2 = new Point2(Integer.valueOf(fieldX1.getText()), Integer.valueOf(fieldY1.getText()));
        event.setP1(p1);
        event.setP2(p2);
        event.setClipPoints(clipPoints);
        event.setWindowPoints(windowPoints);
        return event;
    }

    public void addClipPoint(Point2 point) {
        clipPoints.add(point);
    }

    public void addWindowPoint(Point2 point) {
        windowPoints.add(point);
    }

}