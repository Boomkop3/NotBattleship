package com.unplayable.Gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ConnectionWindow extends BorderPane {
    public ConnectionWindow(){
        this.setLeft(
            new Label("Enter server ip: ")
        );
        this.setCenter(
            new TextField()
        );
        this.setRight(
            new Button("Connect")
        );
        this.setBottom(
            new Label("Ready to connect...")
        );
    }

    public Label getBottomLabel(){
        return (Label)super.getBottom();
    }
    public Label getLeftLabel(){
        return (Label)super.getLeft();
    }
    public TextField getCenterTextField(){
        return (TextField)super.getCenter();
    }
    public Button getRightButton(){
        return (Button)super.getRight();
    }
}
