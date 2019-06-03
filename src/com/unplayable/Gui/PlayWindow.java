package com.unplayable.Gui;

import com.unplayable.Gui.SeaWorld.SeaWorld;
import com.unplayable.Gui.SeaWorld.States.BoatPlacementState;
import com.unplayable.Gui.SeaWorld.States.ClickToFireState;
import com.unplayable.Gui.SeaWorld.States.WaitForAttackState;
import com.unplayable.Gui.SeaWorld.States.WaitingState;
import com.unplayable.Networking.Connection.Connection;
import com.unplayable.Static.GlobalVariables;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.DataInputStream;
import java.io.IOException;


public class PlayWindow extends BorderPane {
    private Connection connection;
    private SeaWorld seaWorld;
    private Button readyButton;

	public PlayWindow(Connection connection) {
        this.connection = connection;
        this.readyButton = new Button("Ready");
		this.setRight(readyButton);
        this.seaWorld = new SeaWorld((g)->{}, this);
		this.seaWorld.setState(new BoatPlacementState(this.seaWorld));
		this.setCenter(this.seaWorld);

        this.readyButton.setOnAction(event -> {
            try {
                this.connection.writeString(GlobalVariables.ImReadyCommand);
				this.readyButton.setText("Waiting for enemy...");
				this.readyButton.setDisable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.seaWorld.setState(
            	new WaitingState(this.seaWorld)
			);
        });
		connection.readStringAsync((message)->{
			new Thread(()->{
				receiveMessage(message);
			}).start();
		});
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public SeaWorld getSeaWorld() {
		return seaWorld;
	}

	public void setSeaWorld(SeaWorld seaWorld) {
		this.seaWorld = seaWorld;
	}

	public Button getReadyButton() {
		return readyButton;
	}

	public void setReadyButton(Button readyButton) {
		this.readyButton = readyButton;
	}

    private void receiveMessage(String message){
		Thread.yield();
    	System.out.println("recieved message: " + message);
		Platform.runLater(()->{
			this.readyButton.setText(message);
		});
		connection.readStringAsync((m)->{
			new Thread(()->{
				receiveMessage(m);
			}).start();
		});
    	switch (message) {
			case GlobalVariables.ClickToFireStateCommand: {
				this.seaWorld.setState(
					new ClickToFireState(this.seaWorld)
				);
				break;
			}
			case GlobalVariables.WaitForAttackCommand: {
				this.seaWorld.setState(
					new WaitForAttackState(this.seaWorld)
				);
				break;
			}
			case GlobalVariables.waitStateCommand: {
				this.seaWorld.setState(
					new WaitingState(this.seaWorld)
				);
				break;
			}
			case GlobalVariables.PlayerWinCommand: {
				// ToDo: Win
				break;
			}
			case GlobalVariables.PlayerLossCommand: {
				// ToDo: Lose
				break;
			}
			default: {
				this.seaWorld.handleInput(message);
				break;
			}
		}
	}
}
