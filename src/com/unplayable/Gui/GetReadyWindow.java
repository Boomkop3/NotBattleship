package com.unplayable.Gui;

import com.unplayable.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GetReadyWindow extends BorderPane {
    private Canvas shipList;
    private Button ready;

    public GetReadyWindow() {
        this.shipList = new Canvas();
        this.ready = new Button("ready");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(this.ready);

        this.setCenter(this.shipList);
        this.setBottom(buttons);
    }

    public void setReady(Button ready) {
        this.ready = ready;
    }

    public Button getReady() {
        return ready;
    }
}
