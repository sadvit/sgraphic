package com.sadvit.test;

import com.sadvit.mediator.DrawMosaicEvent;
import com.sadvit.mediator.EventBus;
import javafx.scene.control.Button;

public class Dialog {

    private EventBus eventBus;

    void test() {
        Button b = new Button("aa");
        b.setOnAction(event -> {
            // что нибудь делаем
            eventBus.handleEvent(new DrawMosaicEvent(10));
        });
    }



}
