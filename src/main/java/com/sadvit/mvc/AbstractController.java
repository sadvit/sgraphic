package com.sadvit.mvc;

import com.sadvit.communication.Event;
import com.sadvit.communication.Handler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class AbstractController<M extends AbstractModel> implements Initializable {

    private M model;

    public abstract void initialize(URL location, ResourceBundle resources);

    public abstract void attachHandlers();

    public abstract void refresh();

    protected void fireEvent(Event event) {
        getModel().getEventBus().fireEvent(event);
    }

    protected <T extends Event> void registerHandler(Class<T> eventType, Handler<T> eventHandler) {
        getModel().getEventBus().registerHandler(eventType, eventHandler);
    }

    protected void unregisterHandler(Class<? extends Event > eventType) {
        getModel().getEventBus().unregisterHandler(eventType);
    }

    public M getModel() {
        return model;
    }

    public AbstractController(M model) {
        this.model = model;
        attachHandlers();
    }
}