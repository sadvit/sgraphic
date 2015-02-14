package com.sadvit.mvc;

import com.sadvit.mediator.MEvent;
import com.sadvit.mediator.MHandler;

public abstract class AbstractController<M extends AbstractModel> {

    private M model;

    public abstract void initialize();

    public abstract void attachHandlers();

    protected void registerHandler(MHandler<? extends MEvent> handler) {
        getModel().getEventBus().registerHandler(handler);
    }

    public AbstractController() {
        initialize();
        attachHandlers();
    }

    public M getModel() {
        return model;
    }

    public AbstractController(M model) {
        this.model = model;
    }

}