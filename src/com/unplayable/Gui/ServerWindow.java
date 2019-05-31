package com.unplayable.Gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jfree.fx.FXGraphics2D;

public class ServerWindow extends BorderPane {

    private VBox player1Box;
    private VBox player2Box;

    private SeaWorld player1World;
    private SeaWorld player2World;

    private Label player1Top;
    private Label player2Top;
    private Label player1Hits;
    private Label player2Hits;
    private Label player1HitsRecieved;
    private Label player2HitsRecieved;
    private Label player1Kills;
    private Label player2Kills;
    private Label player1Deaths;
    private Label player2Deaths;
    private Label player1HitsToGo;
    private Label player2HitsToGo;
    private Label player1KillsToGo;
    private Label player2KillsToGo;
    private Button getStats;

    public ServerWindow() {
        this.player1Top = new Label("Player 1: ");
        this.player2Top = new Label("Player 2: ");

        this.player1Box = new VBox();
        this.player1World = new SeaWorld((g) -> draw(g), this);
        this.player1Hits = new Label("Hits: 0");
        this.player1HitsRecieved = new Label("Hits recieved: 0");
        this.player1Kills = new Label("Kills: 0");
        this.player1Deaths = new Label("Deaths: 0");
        this.player1HitsToGo = new Label("Hits to go: 18");
        this.player1KillsToGo = new Label("Kills to go: 6");
        this.player1Box.setMaxWidth(420);
        this.player1Box.getChildren().addAll(this.player1Top, this.player1World, this.player1Hits, this.player1HitsRecieved, this.player1Kills, this.player1Deaths, this.player1HitsToGo, this.player1KillsToGo);

        this.player2Box = new VBox();
        this.player2World = new SeaWorld((g) -> draw(g), this);
        this.player2Hits = new Label("Hits: 0");
        this.player2HitsRecieved = new Label("Hits recieved: 0");
        this.player2Kills = new Label("Kills: 0");
        this.player2Deaths = new Label("Deaths: 0");
        this.player2HitsToGo = new Label("Hits to go: 18");
        this.player2KillsToGo = new Label("Kills to go: 6");
        this.player2Box.setMaxWidth(420);
        this.player2Box.getChildren().addAll(this.player2Top, this.player2World, this.player2Hits, this.player2HitsRecieved, this.player2Kills, this.player2Deaths, this.player2HitsToGo, this.player2KillsToGo);

        this.getStats = new Button("refresh stats");

        this.setLeft(this.player1Box);
        this.setRight(this.player2Box);
        this.setCenter(this.getStats);
    }

    public Label getPlayer1Top() {
        return player1Top;
    }

    public void setPlayer1Top(Label player1Top) {
        this.player1Top = player1Top;
    }

    public Label getPlayer2Top() {
        return player2Top;
    }

    public void setPlayer2Top(Label player2Top) {
        this.player2Top = player2Top;
    }

    public Label getPlayer1Hits() {
        return player1Hits;
    }

    public void setPlayer1Hits(Label player1Hits) {
        this.player1Hits = player1Hits;
    }

    public Label getPlayer2Hits() {
        return player2Hits;
    }

    public void setPlayer2Hits(Label player2Hits) {
        this.player2Hits = player2Hits;
    }

    public Label getPlayer1HitsRecieved() {
        return player1HitsRecieved;
    }

    public void setPlayer1HitsRecieved(Label player1HitsRecieved) {
        this.player1HitsRecieved = player1HitsRecieved;
    }

    public Label getPlayer2HitsRecieved() {
        return player2HitsRecieved;
    }

    public void setPlayer2HitsRecieved(Label player2HitsRecieved) {
        this.player2HitsRecieved = player2HitsRecieved;
    }

    public Label getPlayer1Kills() {
        return player1Kills;
    }

    public void setPlayer1Kills(Label player1Kills) {
        this.player1Kills = player1Kills;
    }

    public Label getPlayer2Kills() {
        return player2Kills;
    }

    public void setPlayer2Kills(Label player2Kills) {
        this.player2Kills = player2Kills;
    }

    public Label getPlayer1Deaths() {
        return player1Deaths;
    }

    public void setPlayer1Deaths(Label player1Deaths) {
        this.player1Deaths = player1Deaths;
    }

    public Label getPlayer2Deaths() {
        return player2Deaths;
    }

    public void setPlayer2Deaths(Label player2Deaths) {
        this.player2Deaths = player2Deaths;
    }

    public Label getPlayer1HitsToGo() {
        return player1HitsToGo;
    }

    public void setPlayer1HitsToGo(Label player1HitsToGo) {
        this.player1HitsToGo = player1HitsToGo;
    }

    public Label getPlayer2HitsToGo() {
        return player2HitsToGo;
    }

    public void setPlayer2HitsToGo(Label player2HitsToGo) {
        this.player2HitsToGo = player2HitsToGo;
    }

    public Label getPlayer1KillsToGo() {
        return player1KillsToGo;
    }

    public void setPlayer1KillsToGo(Label player1KillsToGo) {
        this.player1KillsToGo = player1KillsToGo;
    }

    public Label getPlayer2KillsToGo() {
        return player2KillsToGo;
    }

    public void setPlayer2KillsToGo(Label player2KillsToGo) {
        this.player2KillsToGo = player2KillsToGo;
    }

    public Button getGetStats() {
        return getStats;
    }

    public void setGetStats(Button getStats) {
        this.getStats = getStats;
    }

    public VBox getPlayer1Box() {
        return player1Box;
    }

    public void setPlayer1Box(VBox player1Box) {
        this.player1Box = player1Box;
    }

    public VBox getPlayer2Box() {
        return player2Box;
    }

    public void setPlayer2Box(VBox player2Box) {
        this.player2Box = player2Box;
    }

    public SeaWorld getPlayer1World() {
        return player1World;
    }

    public void setPlayer1World(SeaWorld player1World) {
        this.player1World = player1World;
    }

    public SeaWorld getPlayer2World() {
        return player2World;
    }

    public void setPlayer2World(SeaWorld player2World) {
        this.player2World = player2World;
    }


    public void draw(FXGraphics2D g){

    }
}
