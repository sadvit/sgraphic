package com.sadvit.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationRunner extends Application {

    private FXMLLoader loader = new FXMLLoader(getClass().getResource("ApplicationView.fxml"));

    public static final int INITIAL_WINDOW_SIZE = 700;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = loader.load();
        ApplicationController controller = loader.getController();
        controller.initialize(stage);
        Scene scene = new Scene(root, INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
        stage.setTitle("SGraphic");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}