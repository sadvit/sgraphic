package com.sadvit.dialog;

import com.sadvit.ui.Loader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class AbstractDialog<T> extends Stage {

    private T controller;

    private String title;

    private int width;

    private int height;

    public AbstractDialog(String viewName, Class<T> controllerClass, String title, int width, int height) {
        controller = Loader.load(viewName);
        //controller = Loader.get(viewName);
        this.title = title;
        this.width = width;
        this.height = height;
        initialize();
    }

    public abstract Node getRootView();

    protected T getController() {
        return controller;
    }

    public void showDialog() {
        setResizable(false);
        setTitle(title);
        setScene(new Scene((Parent) getRootView(), width, height));
        show();
    }

    public void hideDialog() {
        hide();
    }

    protected abstract void initialize();

}