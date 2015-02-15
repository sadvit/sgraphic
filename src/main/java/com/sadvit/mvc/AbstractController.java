package com.sadvit.mvc;

import com.sadvit.communication.MEvent;
import com.sadvit.communication.MHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractController<M extends AbstractModel> implements Initializable {

    private M model;

    public abstract void initialize(URL location, ResourceBundle resources);

    public abstract void attachHandlers();

    public abstract void refresh();

    protected void registerHandler(MHandler<? extends MEvent> handler) {
        getModel().getEventBus().registerHandler(handler);
    }

    protected void unregisterHandler(MHandler<? extends MEvent> handler) {
        getModel().getEventBus().unregisterHandler(handler);
    }

    public M getModel() {
        return model;
    }

    public AbstractController(M model) {
        this.model = model;
        attachHandlers();
    }
}