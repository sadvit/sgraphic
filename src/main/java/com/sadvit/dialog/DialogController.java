package com.sadvit.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public interface DialogController {

    public default void setDrawActionHandler(EventHandler<ActionEvent> handler) {
        getButtonDraw().setOnAction(handler);
    }

    public default void setCancelActionHandler(EventHandler<ActionEvent> handler) {
        getButtonCancel().setOnAction(handler);
    }

    public Button getButtonDraw();

    public Button getButtonCancel();

    public GridPane getRoot();

}
