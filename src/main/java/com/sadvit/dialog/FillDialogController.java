package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.draw.FillType;
import com.sadvit.event.DrawFillEvent;
import com.sadvit.math.Point2;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class FillDialogController implements DialogController {

    private Point2 point;
    public Button buttonCancel;
    public Button buttonDraw;
    public ColorPicker colorOutline;
    public ColorPicker colorBeginInline;
    public ColorPicker colorEndInline;
    public GridPane root;
    public ComboBox<FillType> fillTypeBox;

    public Point2 getPoint() {
        return point;
    }

    public void setPoint(Point2 point) {
        this.point = point;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillTypeBox.setItems(FXCollections.observableArrayList(
                FillType.SCANLINE,
                FillType.SECTION,
                FillType.SEED
        ));
        fillTypeBox.setValue(FillType.SCANLINE);
        colorBeginInline.setValue(Color.BLACK);
    }

    public void draw() {
        EventBus.getInstance().fireEvent(getEvent());
    }

    public DrawFillEvent getEvent() {
        DrawFillEvent event = new DrawFillEvent();
        event.setColorInlineEnd(colorEndInline.getValue());
        event.setColorInlineStart(colorBeginInline.getValue());
        event.setColorOutline(colorOutline.getValue());
        event.setFillType(fillTypeBox.getValue());
        event.setPoint(point);
        return event;
    }
}