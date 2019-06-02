package com.unplayable;

import com.unplayable.Gui.ServerWindow;
import com.unplayable.Networking.Connection;
import com.unplayable.Networking.ConnectionManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ServerMain {
    public static void main(String[] args) {
        System.out.println("Running server...");
        ConnectionManager manager = ConnectionManager.getInstance();
        manager.getInstance().setOnConnection(
            ()->{
                System.out.println("clients connected: " + manager.getIncomingConnections().size());
            }
        );
    }
}
