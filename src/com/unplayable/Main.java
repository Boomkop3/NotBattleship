package com.unplayable;

import com.unplayable.Gui.ConnectionWindow;
import com.unplayable.Gui.PlayWindow;
import com.unplayable.Networking.Connection.Connection;
import com.unplayable.Networking.ConnectionManager;
import com.unplayable.Static.GlobalVariables;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
	ConnectionWindow connectionWindow;

    public static void main(String[] args){
        launch(Main.class);
    }
    @Override
    public void start(Stage stage) throws Exception {
		this.connectionWindow = new ConnectionWindow();
    	stage.setScene(
    		new Scene(
    			connectionWindow
			)
		);
    	stage.show();
		this.connectionWindow.getRightButton().setOnAction((e)->{
			String serverIp = ((TextField)this.connectionWindow.getCenter()).getText();
			try {
				this.connectionWindow.getBottomLabel().setText("Connecting...");
				this.startGame(serverIp, stage);
			} catch (IOException ex) {
				ex.printStackTrace();
				this.connectionWindow.getBottomLabel().setText("Failed to connect...");
			}
		});
    }

	@Override
	public void stop() throws Exception {
		ConnectionManager.close();
		super.stop();
	}

    private void startGame(String serverIP, Stage stage) throws IOException {
		this.connectionWindow.getBottomLabel().setText("Starting game on server: " + serverIP);
    	ConnectionManager manager = ConnectionManager.getInstance();
    	Connection serverConnection = manager.createConnection(serverIP);
    	this.connectionWindow.getBottomLabel().setText("Connected to: " + serverConnection.getAdress() + ". Waiting for an enemy to connect...");
    	this.connectionWindow.getRightButton().setOnAction(null);
		serverConnection.readStringAsync((result)->{
			if (result.equals(GlobalVariables.startGameCommand)){
				Platform.runLater(()->{
					this.connectionWindow.getBottomLabel().setText("Starting game...");
					stage.hide();
					stage.setScene(
						new Scene(
							new PlayWindow(serverConnection)
						)
					);
					stage.show();
				});
			}
		});
	}
}
