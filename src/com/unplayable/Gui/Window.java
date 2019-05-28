package com.unplayable.Gui;

import com.unplayable.Main;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Window extends BorderPane {
    private Canvas shipList;
    private Button rotate;
    private Button ready;

    public Window() {
        this.shipList = new Canvas();
        this.rotate = new Button("rotate ship");
        this.ready = new Button("ready");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(this.rotate, this.ready);

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
