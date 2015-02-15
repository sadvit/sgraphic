package com.sadvit.dialog;

import com.sadvit.ui.Loader;
import javafx.scene.Node;
import javafx.stage.Stage;


public abstract class AbstractDialog<T> extends Stage {

    private T controller;

    public AbstractDialog(String viewName, Class<T> controllerClass) {
        controller = Loader.load(viewName);
    }

    public abstract Node getRootView();

    protected T getController() {
        return controller;
    }

}


/*
        try {

            Parent root = Loader.load("MosaicDialogView.fxml");
            textField = (TextField) root.lookup("#input");
            Button buttonOk = (Button) root.lookup("#buttonOk");
            buttonOk.setOnAction(event -> );


            buttonCancel.setOnAction(buttonCancelHandler);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle(dialogName);
            stage.setScene(new Scene(root, 200, 100));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/