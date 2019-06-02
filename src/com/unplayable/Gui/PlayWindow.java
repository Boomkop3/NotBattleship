package com.unplayable.Gui;

import com.unplayable.Networking.Connection;
import com.unplayable.Static.GlobalVariables;
import com.unplayable.Static.ResourceReader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.FXGraphics2D;

import java.io.IOException;
import java.util.List;


public class PlayWindow extends BorderPane {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private List<String> getShips(){
        try {
            return ResourceReader.getInstance().getResourceDirectory("/Sprites/ships");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PlayWindow(Connection connection) {
        this.connection = connection;
        GetReadyWindow getReadyWindow = new GetReadyWindow();
        Button readyButton = getReadyWindow.getReady();
        SeaWorld world = new SeaWorld((g)->draw(g), this);
        readyButton.setOnAction(event -> {
            try {
                this.connection.sendObject(GlobalVariables.ImReadyCommand);
            } catch (IOException e) {
                e.printStackTrace();
            }
            world.setInGame(true);
            this.setRight(new InGameWindow());
        });
        getReadyWindow.setReady(readyButton);
        this.setCenter(
                world
        );
        this.setRight(getReadyWindow);
    }

    public void draw(FXGraphics2D g) {
        // Heck yeah!
    }
}
