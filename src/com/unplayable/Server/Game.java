package com.unplayable.Server;

import com.unplayable.Networking.Connection;
import com.unplayable.Static.GlobalVariables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Connection[] players;
    public Game(Connection player1, Connection player2) throws IOException, InterruptedException {
        this.players = new Connection[] {player1, player2};
        System.out.println("starting game between: ");

        Arrays.stream(players).parallel().forEach((connection)->{
            System.out.println("> " + connection.getIp());
        });

        List<Thread> waitingPlayers = new ArrayList<>();
        for (Connection player : this.players){
            player.sendObject(GlobalVariables.startGameCommand);
            Thread waitingThread = new Thread(()->{
                try {
                    player.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            waitingPlayers.add(
                waitingThread
            );
            waitingThread.start();
        }
        for (Thread thread : waitingPlayers){
            thread.join();
        }
        System.out.println("All players ready!");
    }
}
