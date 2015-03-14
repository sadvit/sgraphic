package com.sadvit.dialog;

import com.sadvit.ui.Loader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class AbstractDialog<T extends DialogController> extends Stage {

    private T controller;

    private String title;

    private int width;

    private int height;

    public AbstractDialog(String viewName, String title, int width, int height) {
        controller = Loader.get(viewName);
        this.title = title;
        this.width = width;
        this.height = height;
        initialize();
    }

    public Node getRootView() {
        return getController().getRoot();
    }

    protected T getController() {
        return controller;
    }

    public void showDialog() {
        setResizable(false);
        setTitle(title);
        if (getScene() == null)
            setScene(new Scene((Parent) getRootView(), width, height));
        show();
        sizeToScene();
    }

    public void hideDialog() {
        hide();
    }

    protected void initialize() {
        controller.setCancelActionHandler(event -> hideDialog());
    }

}