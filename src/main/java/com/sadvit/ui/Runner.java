package com.sadvit.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationController controller = Loader.get("ApplicationView.fxml");
        controller.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
