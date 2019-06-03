package com.unplayable.Server;

import com.unplayable.Networking.Connection.Connection;
import com.unplayable.Networking.ConnectionManager;
import com.unplayable.Static.GlobalVariables;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Connection[] players;

    public Game(Connection player1, Connection player2) throws IOException {
		this.players = new Connection[] {player1, player2};
		System.out.println("starting game between: ");
		Arrays.stream(players).parallel().forEach((connection)->{
			System.out.println("> " + connection.getAdress());
		});

        List<Thread> waitingPlayers = new ArrayList<>();
        for (Connection player : this.players){
            player.writeString(GlobalVariables.startGameCommand);
            Thread waitingThread = new Thread(()->{
                try {
                    player.readString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            waitingPlayers.add(
                waitingThread
            );
            waitingThread.start();
        }
        for (Thread thread : waitingPlayers){
			try {
				thread.join();
			} catch (InterruptedException e) {
				return;
			}
		}
        System.out.println("All players ready!");
        System.out.println("Starting match!");

		player1.readStringAsync((message)->{
			new Thread(()->{
				handleMessage(message, player1, player2);
			}).start();
		});
        player2.readStringAsync((message)->{
        	new Thread(()->{
				handleMessage(message, player2, player1);
			}).start();
		});

		player1.writeString(GlobalVariables.ClickToFireStateCommand);
		player2.writeString(GlobalVariables.WaitForAttackCommand);
    }

    private void handleMessage(String message, Connection fromPlayer, Connection otherPlayer){
		fromPlayer.readStringAsync((m)->{
			if (m == null) {
				System.out.println("Stopping game");
				try {
					otherPlayer.writeString(GlobalVariables.waitStateCommand);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			};
			new Thread(()->{
				handleMessage(m, fromPlayer, otherPlayer);
			}).start();
		});
		Thread.yield();
    	System.out.println("Passing message between " + fromPlayer.getAdress() + " and " + otherPlayer.getAdress());
		try {
			otherPlayer.writeString(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
