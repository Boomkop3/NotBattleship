package com.unplayable;

import com.unplayable.Gui.SeaWorld;
import com.unplayable.Gui.Window;
import com.unplayable.Gui.WindowTwo;
import com.unplayable.Static.ImageLibrary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Main extends Application {
    private BorderPane borderPane;

    public static void main(String[] args) throws Exception{
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.borderPane = new BorderPane();
        Window window = new Window();
        Button readyButton = window.getReady();
        readyButton.setOnAction(event -> {
            borderPane.setRight(
                    new WindowTwo()
            );
        });
        window.setReady(readyButton);
        borderPane.setCenter(
            new SeaWorld((g)->draw(g), borderPane)
        );
        borderPane.setRight(
                window
        );
        stage.setScene(
            new Scene(
                borderPane
            )
        );
        stage.show();
    }

    public void draw(FXGraphics2D g){

    }

    public void update(){

    }

}
