package com.sadvit.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;


public class Dialog extends Stage {

    private TextField textField;
/*
    public Dialog(ApplicationController controller) {
        try {
            Parent root = FXMLLoader.load(fxml);
            Button buttonOk = (Button) root.lookup("#buttonOk");
            buttonOk.setOnAction(buttonOkHandler);
            Button buttonCancel = (Button) root.lookup("#buttonCancel");
            textField = (TextField) root.lookup("#input");
            buttonCancel.setOnAction(buttonCancelHandler);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(dialogName);
            stage.setScene(new Scene(root, 200, 100));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    public Dialog(URL fxml, String dialogName, EventHandler<ActionEvent> buttonOkHandler, EventHandler<ActionEvent> buttonCancelHandler) {

    }

    public int getValue() {
        return Integer.parseInt(textField.getText());
    }
}
