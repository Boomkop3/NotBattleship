package com.unplayable;

import com.unplayable.Gui.ServerWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerMain extends Application {

    public static void main(String[] args) {
        launch(ServerMain.class);
    }
    @Override
    public void start(Stage primaryStage) {
        ServerWindow serverWindow = new ServerWindow();
        primaryStage.setScene(new Scene(serverWindow));
        primaryStage.show();
    }
}
