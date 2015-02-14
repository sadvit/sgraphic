package com.sadvit.ui;

import com.sadvit.image.SimpleImage;
import com.sadvit.image.SimpleImageUtils;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ApplicationController {

    @FXML
    private Pane pane;

    @FXML
    private ImageCanvas drawArea;

    private Stage stage;

    public void onExitClick() {
        Runtime.getRuntime().exit(0);
    }

    public void onSaveClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save BMP image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            SimpleImage image = drawArea.getImage();
            SimpleImageUtils.write(image, getPath(file));
        }
    }

    public void onOpenClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("BMP files (*.bmp)", "*.bmp");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open BMP image");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            SimpleImage image = SimpleImageUtils.read(file.getAbsolutePath());
            drawArea.setImage(image);
            refresh();
        }
    }

    public void initialize(Stage primaryStage) {
        drawArea.widthProperty().bind(pane.widthProperty());
        drawArea.heightProperty().bind(pane.heightProperty());
        stage = primaryStage;
        drawArea.setImage(SimpleImageUtils.create(ApplicationRunner.INITIAL_WINDOW_SIZE, ApplicationRunner.INITIAL_WINDOW_SIZE));
        refresh();
    }

    private String getPath(File file) {
        return file.getAbsolutePath() + ".bmp";
    }

    public void onRandomClick() {

       // }




/*
        SimpleImage image = drawArea.getImage();
        for (int i = 0; i < image.getWidth() - 1; i += 10) {
            for (int j = 0; j < image.getHeight() - 1; j += 10) {
                Color c = randomColor();
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        image.setColor(i + k, j + l, c);
                    }
                }
            }
        }
        refresh();*/
    }

    public void drawMosaicAction() {
        SimpleImage image = drawArea.getImage();
        for (int i = 0; i < image.getWidth() - 1; i += 10) {
            for (int j = 0; j < image.getHeight() - 1; j += 10) {
                Color c = randomColor();
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        image.setColor(i + k, j + l, c);
                    }
                }
            }
        }
        refresh();
    }

    private void refresh() {
        drawArea.refresh();
        stage.setWidth(drawArea.getImage().getWidth());
        stage.setHeight(drawArea.getImage().getHeight());
    }

    private Color randomColor() {
        return new Color(Math.random(), Math.random(), Math.random(), 1);
    }

}
