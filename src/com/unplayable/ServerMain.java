package com.unplayable;

import com.unplayable.Gui.ServerWindow;
import com.unplayable.Networking.Connection;
import com.unplayable.Networking.ConnectionManager;
import com.unplayable.Server.Game;
import com.unplayable.Static.GlobalVariables;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerMain {
    private static List<Connection> connectedPlayers;
    public static void main(String[] args) throws IOException {
        connectedPlayers = new ArrayList<>();
        System.out.println("Running server...");
        ConnectionManager manager = ConnectionManager.getInstance();
        manager.startServer();
        manager.getInstance().setOnConnection(
            ()->{
                List<Connection> connections = manager.getIncomingConnections();
                int connectionCount = connections.size();
                System.out.println("clients connected: " + connectionCount);
                if (connectionCount > 0 && connectionCount % 2 == 0){
                    startGame(
                        connections.get(connectionCount-2),
                        connections.get(connectionCount-1)
                    );
                }
            }
        );
        System.out.println("Server running");
    }

    private static void startGame(Connection player1, Connection player2) {
        new Game(player1, player2);
    }
}
