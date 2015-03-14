package com.sadvit.ui;

import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Используется для загрузки View и Controller из FXML файлов, находящихся в папке recourses/ui
 */
public class Loader {

    public static final String FOLDER = "ui/";

    private static HashMap<String, Object> controllers = new HashMap<>();

    static {
        init();
    }

    /**
     * TODO добавить загрузку всех контроллеров при инициализации приложения
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

    @SuppressWarnings("unchecked")
    public static <T> T get(String name) {
        return (T) controllers.get(name);
    }

    public static void init() {
        File folder = new File(FOLDER);
        System.out.println("fff " + folder.isFile());
        System.out.println(folder.getName());
        File[] files = folder.listFiles();
        if (files != null)
            for (File file : files) {
                if (file.isFile())
                    controllers.put(file.getName(), load(file.getName()));
            }
    }

}
