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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

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
		connectionWindow.getRightButton().setOnAction((e)->{
			String serverIp = ((TextField)connectionWindow.getCenter()).getText();
			try {
				connectionWindow.getBottomLabel().setText("Connecting...");
				this.startGame(serverIp, stage);
				stage.hide();
			} catch (IOException ex) {
				ex.printStackTrace();
				connectionWindow.getBottomLabel().setText("Failed to connect...");
			}
		});
    }

    private void startGame(String serverIP, Stage stage) throws IOException {
    	System.out.println("Starting game on server: " + serverIP);
    	ConnectionManager manager = ConnectionManager.getInstance();
    	Connection serverConnection = manager.createConnection(serverIP);
    	System.out.println("Connected to: " + serverConnection.getAdress());

    	stage.show();
	}
}
