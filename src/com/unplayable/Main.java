package com.unplayable;

import com.unplayable.Gui.SeaWorld;
import com.unplayable.Static.ImageLibrary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Main extends Application {

    public static void main(String[] args) throws Exception{
        launch(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(
            new SeaWorld((g)->draw(g), borderPane)
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
