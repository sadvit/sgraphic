package com.sadvit.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       // ApplicationController controller = Loader.load("ApplicationView.fxml", "com.sadvit.ui", ApplicationController.class);
    }

    public static void main(String[] args) {
        Loader.load("ApplicationView.fxml");
    }

}
