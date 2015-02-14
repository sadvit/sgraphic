package com.sadvit.test;

import com.sadvit.mediator.DrawMosaicEvent;
import com.sadvit.mediator.DrawMosaicHandler;
import com.sadvit.mvc.AbstractController;

public class MyController extends AbstractController<MyModel> {

    @Override
    public void initialize() {

    }

    @Override
    public void attachHandlers() {
        registerHandler(new DrawMosaicHandler() {
            @Override
            public void handle(DrawMosaicEvent event) {

            }
        });
    }

}
