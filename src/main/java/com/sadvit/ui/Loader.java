package com.sadvit.ui;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Используется для загрузки View и Controller из FXML файлов, находящихся в папке recourses/ui
 */
public class Loader {

    public static final String FOLDER = "ui/";

    /**
     * Возвращает ссылку на связанный контроллер.
     */
    public static <T> T load(String name) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(Loader.class.getClassLoader().getResourceAsStream(FOLDER + name));
            return fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
