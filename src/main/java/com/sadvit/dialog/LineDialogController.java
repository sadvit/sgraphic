package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.event.DrawLineEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class LineDialogController implements DialogController {

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
        EventBus.getInstance().fireEvent(new DrawLineEvent());
    }

}
