package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.event.DrawMosaicEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MosaicDialogController implements DialogController {

    @FXML
    private Button buttonDraw;

    @FXML
    private Button buttonCancel;

    @FXML
    private GridPane root;

    @FXML
    private TextField input;

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Button getButtonDraw() {
        return buttonDraw;
    }

    public GridPane getRoot() {
        return root;
    }

    public void draw() {
        int size = Integer.parseInt(input.getText());
        EventBus.getInstance().fireEvent(new DrawMosaicEvent(size));
    }

}
