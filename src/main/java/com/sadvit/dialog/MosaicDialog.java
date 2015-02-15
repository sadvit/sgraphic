package com.sadvit.dialog;

import com.sadvit.communication.EventBus;
import com.sadvit.event.DrawMosaicEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MosaicDialog extends AbstractDialog<MosaicDialogController> {

    public MosaicDialog() {
        super("MosaicDialogView.fxml", MosaicDialogController.class);
        initialize();
    }

    @Override
    public Node getRootView() {
        return getController().getRoot();
    }

    private void initialize() {
        getController().getButtonOk().setOnAction(event -> {
            int value = Integer.parseInt(getController().getInput().getText());
            EventBus.getInstance().handleEvent(new DrawMosaicEvent(value));
            hide();
        });
        getController().getButtonCancel().setOnAction(event -> hide());
    }

    public void showDialog() {
        setResizable(false);
        setTitle("test");
        setScene(new Scene((Parent) getRootView(), 200, 100));
        show();
    }

    public void hideDialog() {
        hide();
    }

}
