package com.sadvit.ui;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;

/**
 * Должен загружать контроллер из FXML и возвращать ссылку на инициализированный контроллер.
 */
public class Loader {

    public static final String FOLDER = "ui/";

    public static <T> T load(String name) {
        URL location = Loader.class.getClassLoader().getResource(FOLDER + name);
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            fxmlLoader.load();
            return fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
