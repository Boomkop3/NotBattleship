package com.unplayable.Gui;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GameStateView extends BorderPane {
    private VBox ownStats;
    private VBox otherStats;
    private Label infoSelf;
    private Label infoOther;
    private Label ownHitsText;
    private Label otherHitsText;
    private Label ownHitsRecievedText;
    private Label otherHitsRecievedText;
    private Label ownKillsText;
    private Label otherKillsText;
    private Label ownDeathsText;
    private Label otherDeathsText;
    private Label ownHitsToGoText;
    private Label otherHitsToGoText;
    private Label ownKillsToGoText;
    private Label otherKillsToGoText;
    private Score score;

    public Score getScore(){
        return this.score;
    }

    public void setScore(Score score){
        this.score = score;
        this.updateInterface();
    }

    public void initInterFace(){
        this.infoSelf = new Label("own stats:");
        this.infoOther = new Label("opponent's stats:");
        this.ownHitsText = new Label("your hits: " + this.score.getHits());
        this.otherHitsText = new Label("opponent's hits: " + this.score.getHitsRecieved());
        this.ownHitsRecievedText = new Label("own hits recieved: " + this.score.getHitsRecieved());
        this.otherHitsRecievedText = new Label("opponent's hits recieved: " + this.score.getHits());
        this.ownKillsText = new Label("own kills: " + this.score.getKills());
        this.otherKillsText = new Label("opponent's kills: " + this.score.getDeaths());
        this.ownDeathsText = new Label("own deaths: " + this.score.getDeaths());
        this.otherDeathsText = new Label("opponent's deaths: " + this.score.getKills());
        this.ownHitsToGoText = new Label("own hits to go: " + this.score.getOwnHitsToGo());
        this.otherHitsToGoText = new Label("opponent's hits to go: " + this.score.getOtherHitsToGo());
        this.ownKillsToGoText = new Label("own kills to go: " + this.score.getOwnKillsToGo());
        this.otherKillsToGoText = new Label("opponent's kills to go: " + this.score.getOtherKillsToGo());
    }

    public void updateInterface(){
        Platform.runLater(
            this::runUpdateInterface
        );
    }

    private void runUpdateInterface(){
        this.infoSelf.setText("own stats:");
        this.infoOther.setText("opponent's stats:");
        this.ownHitsText.setText("your hits: " + this.score.getHits());
        this.otherHitsText.setText("opponent's hits: " + this.score.getHitsRecieved());
        this.ownHitsRecievedText.setText("own hits recieved: " + this.score.getHitsRecieved());
        this.otherHitsRecievedText.setText("opponent's hits recieved: " + this.score.getHits());
        this.ownKillsText.setText("own kills: " + this.score.getKills());
        this.otherKillsText.setText("opponent's kills: " + this.score.getDeaths());
        this.ownDeathsText.setText("own deaths: " + this.score.getDeaths());
        this.otherDeathsText.setText("opponent's deaths: " + this.score.getKills());
        this.ownHitsToGoText.setText("own hits to go: " + this.score.getOwnHitsToGo());
        this.otherHitsToGoText.setText("opponent's hits to go: " + this.score.getOtherHitsToGo());
        this.ownKillsToGoText.setText("own kills to go: " + this.score.getOwnKillsToGo());
        this.otherKillsToGoText.setText("opponent's kills to go: " + this.score.getOtherKillsToGo());
    }

    public GameStateView(int hitsToGo, int killsToGo) {
        this.score = new Score(
            0,
            0,
            0,
            0,
            hitsToGo,
            hitsToGo,
            killsToGo,
            killsToGo
        );
        this.initInterFace();

        this.ownStats = new VBox();
        this.otherStats = new VBox();
        this.ownStats.getChildren().addAll(
                this.ownHitsText,
                this.ownHitsRecievedText,
                this.ownKillsText,
                this.ownDeathsText,
                this.ownHitsToGoText,
                this.ownKillsToGoText);
        this.otherStats.getChildren().addAll(
                this.otherHitsText,
                this.otherHitsRecievedText,
                this.otherKillsText,
                this.otherDeathsText,
                this.otherHitsToGoText,
                this.otherKillsToGoText);

        this.setLeft(this.ownStats);
        this.setRight(this.otherStats);
    }
}
