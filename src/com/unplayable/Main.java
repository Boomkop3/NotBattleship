package com.unplayable;

import com.unplayable.Gui.SeaWorld;
import com.unplayable.Gui.GetReadyWindow;
import com.unplayable.Gui.InGameWindow;
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
        GetReadyWindow getReadyWindow = new GetReadyWindow();
        Button readyButton = getReadyWindow.getReady();
        readyButton.setOnAction(event -> {
            borderPane.setRight(
				new InGameWindow()
            );
        });
        getReadyWindow.setReady(readyButton);
        SeaWorld seaWorld = new SeaWorld((g)->draw(g), borderPane);
        borderPane.setCenter(
        	seaWorld
        );
        
        borderPane.setRight(
			getReadyWindow
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
