package com.sadvit.ui;

import com.sadvit.dialog.Dialogs;
import com.sadvit.event.DrawMosaicEvent;
import com.sadvit.event.DrawMosaicHandler;
import com.sadvit.mvc.AbstractController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController extends AbstractController<ApplicationModel> {

    public static final int INITIAL_WINDOW_SIZE = 400;

    @FXML
    private BorderPane root;

    @FXML
    private Pane pane;

    @FXML
    private ImageCanvas drawArea;

    private Stage primaryStage;

    public void show(Stage stage) {
        primaryStage = stage;
        Scene scene = new Scene(root, INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
        stage.setTitle("SGraphic");
        stage.setScene(scene);
        stage.show();
        refresh();
    }

    public void refresh() {
        drawArea.draw(getModel().getCurrentImage());
        primaryStage.setResizable(false);
        primaryStage.setWidth(getModel().getCurrentImage().getWidth());
        primaryStage.setHeight(getModel().getCurrentImage().getHeight());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawArea.widthProperty().bind(pane.widthProperty());
        drawArea.heightProperty().bind(pane.heightProperty());
        getModel().createWhiteImage(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
    }

    @Override
    public void attachHandlers() {
        registerHandler(new DrawMosaicHandler() {
            @Override
            public void handle(DrawMosaicEvent event) {
                drawMosaicAction(event.getSize());
            }
        });
    }

    public void onExitClick() {
        Runtime.getRuntime().exit(0);
    }

    public void onSaveClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save BMP image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            getModel().saveImage(file.getAbsolutePath());
        }
    }

    public void onOpenClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open BMP image");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            getModel().openImage(file.getAbsolutePath());
            refresh();
        }
    }

    public void onMosaicClick() {
        Dialogs.showMosaicDialog();
    }

    private void drawMosaicAction(int size) {
        getModel().createMosaicImage(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE, size);
        refresh();
    }

    public ApplicationController() {
        super(new ApplicationModel());
    }

}
