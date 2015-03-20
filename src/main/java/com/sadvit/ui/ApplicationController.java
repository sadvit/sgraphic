package com.sadvit.ui;

import com.sadvit.dialog.Dialogs;
import com.sadvit.event.DrawCircleEvent;
import com.sadvit.event.DrawCurveEvent;
import com.sadvit.event.DrawLineEvent;
import com.sadvit.event.DrawMosaicEvent;
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

    public static final int INITIAL_WINDOW_SIZE = 600;
    public static final int PANEL_SIZE = 54;

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
        primaryStage.setHeight(getModel().getCurrentImage().getHeight() + PANEL_SIZE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawArea.widthProperty().bind(pane.widthProperty());
        drawArea.heightProperty().bind(pane.heightProperty());
        getModel().createWhiteImage(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
    }

    @Override
    public void attachHandlers() {
        registerHandler(DrawMosaicEvent.class, this::drawMosaicEvent);
        registerHandler(DrawLineEvent.class, this::drawLineEvent);
        registerHandler(DrawCircleEvent.class, this::drawCircleEvent);
        registerHandler(DrawCurveEvent.class, this::drawCurveEvent);
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
        FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
        FileChooser.ExtensionFilter extFilter2 = new FileChooser.ExtensionFilter("OBJ files (*.obj)", "*.obj");
        fileChooser.getExtensionFilters().add(extFilter1);
        fileChooser.getExtensionFilters().add(extFilter2);
        fileChooser.setTitle("Open BMP or OBJ image");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            switch (file.getName().split("\\.")[1]) {
                case "bmp": {
                    getModel().openBMP(file.getAbsolutePath());
                    refresh();
                    break;
                }
                case "obj": {
                    getModel().openOBJ(file.getAbsolutePath());
                    refresh();
                    break;
                }
            }
        }
    }

    public void onMosaicClick() {
        Dialogs.showMosaicDialog();
    }

    private void drawMosaicEvent(DrawMosaicEvent event) {
        int size = event.getSize();
        getModel().createMosaicImage(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE, size);
        refresh();
    }

    private void drawLineEvent(DrawLineEvent event) {
        getModel().createLine(event);
        refresh();
    }

    private void drawCircleEvent(DrawCircleEvent event) {
        //getModel().createLine(event);
        refresh();
    }

    private void drawCurveEvent(DrawCurveEvent event) {
        //getModel().createLine(event);
        refresh();
    }

    public ApplicationController() {
        super(new ApplicationModel());
    }

    public void onLineClick() {
        Dialogs.showLineDialog();
    }

    public void onCircleClick() {
        Dialogs.showCircleDialog();
    }

    public void onCurvesClick() {
        Dialogs.showCurveDialog();
    }

    public void onNewClick() {
        getModel().createWhiteImage(INITIAL_WINDOW_SIZE, INITIAL_WINDOW_SIZE);
        refresh();
    }
}
