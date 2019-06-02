package com.unplayable;

import com.unplayable.Gui.ConnectionWindow;
import com.unplayable.Gui.SeaWorld;
import com.unplayable.Gui.GetReadyWindow;
import com.unplayable.Gui.InGameWindow;
import com.unplayable.Networking.Connection;
import com.unplayable.Networking.ConnectionManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.io.IOException;

public class Main extends Application {
    private BorderPane borderPane;

    public static void main(String[] args){
        launch(Main.class);
    }
    @Override
    public void start(Stage stage) throws Exception {
		ConnectionWindow connectionWindow = new ConnectionWindow();
    	stage.setScene(
    		new Scene(
    			connectionWindow
			)
		);
    	stage.show();
		((Button)connectionWindow.getRight()).setOnAction((e)->{
			String serverIp = ((TextField)connectionWindow.getCenter()).getText();
			stage.hide();
			try {
				this.startGame(serverIp, stage);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
    }

    private void startGame(String serverIP, Stage stage) throws IOException {
    	System.out.println("Starting game on server: " + serverIP);
    	ConnectionManager manager = ConnectionManager.getInstance();
    	manager.createConnection(serverIP);
    	stage.show();
	}
}
