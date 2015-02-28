package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.event.DrawMosaicEvent;
import javafx.scene.Node;

public class MosaicDialog extends AbstractDialog<MosaicDialogController> {

    public MosaicDialog() {
        super("MosaicDialogView.fxml", MosaicDialogController.class, "Mosaic", 200, 100);
    }

    @Override
    public Node getRootView() {
        return getController().getRoot();
    }

    protected void initialize() {
        getController().getButtonOk().setOnAction(event -> {
            int value = Integer.parseInt(getController().getInput().getText());
            EventBus.getInstance().handleEvent(new DrawMosaicEvent(value));
            hide();
        });
        getController().getButtonCancel().setOnAction(event -> hide());
    }

}
