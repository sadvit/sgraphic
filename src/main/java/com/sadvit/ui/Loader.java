package com.sadvit.ui;

import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Load and init all FXML files from recourses/ui
 */
public class Loader {

    private static final String FOLDER = "ui/";

    private static HashMap<String, Object> controllers = new HashMap<>();

    static {
        init();
    }

    private static <T> T load(String name) {
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

    @SuppressWarnings("ConstantConditions")
    public static void init() {
        try {
            Enumeration<URL> resources = Loader.class.getClassLoader().getResources(FOLDER);
            while (resources.hasMoreElements()) {
                File folder = Paths.get(resources.nextElement().toURI()).toFile();
                try {
                    for (File file : folder.listFiles()) {
                        controllers.put(file.getName(), load(file.getName()));
                    }
                } catch (Exception e) {
                    System.err.println("FXML loading error");
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
