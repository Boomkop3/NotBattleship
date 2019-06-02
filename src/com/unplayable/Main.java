package com.unplayable;

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

public class Main extends Application {
    private BorderPane borderPane;

    public static void main(String[] args){
        launch(Main.class);
    }
	int eventsRecieved = 0;
    @Override
    public void start(Stage stage) throws Exception {
        //BorderPane borderPane = new BorderPane();

		ConnectionManager.getInstance();
		this.borderPane = new BorderPane();
		new Thread(()->{
			try {


				ConnectionManager.getInstance().receiveObject(
					(result, adress)->{
						System.out.println((String)result);
						System.out.println("from: " + adress);
					}
				);


			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(()->{
			try {


				while (true){
					ConnectionManager connectionManager = ConnectionManager.getInstance();
					Connection connection = connectionManager.createConnection("127.0.0.1");
					connection.sendObject("get fkn rekt m8");
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
		borderPane.setCenter(
			new TextField()
		);
		borderPane.setRight(
			new Button("send message")
		);

        stage.setScene(
            new Scene(
                borderPane
            )
        );
        stage.show();
    }

    private void inputReceived(Object object){
    	eventsRecieved++;
    	String wtvr = (String)object;
    	System.out.println(wtvr);
    	System.out.println("recieved " + eventsRecieved);
	}

    public void draw(FXGraphics2D g){

    }

    public void update(){

    }
}
