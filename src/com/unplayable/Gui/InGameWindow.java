package com.unplayable.Gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class InGameWindow extends BorderPane {
    private VBox ownStats;
    private VBox otherStats;
    private Label infoSelf;
    private Label infoOther;
    private int ownHits;
    private Label ownHitsText;
    private Label otherHitsText;
    private int ownHitsRecieved;
    private Label ownHitsRecievedText;
    private Label otherHitsRecievedText;
    private int ownKills;
    private Label ownKillsText;
    private Label otherKillsText;
    private int ownDeaths;
    private Label ownDeathsText;
    private Label otherDeathsText;
    private int ownHitsToGo;
    private int otherHitsToGo;
    private Label ownHitsToGoText;
    private Label otherHitsToGoText;
    private int ownKillsToGo;
    private int otherKillsToGo;
    private Label ownKillsToGoText;
    private Label otherKillsToGoText;
    private Button fire;

    public Score getScore(){
        return new Score(
            this.ownHits,
            this.ownHitsRecieved,
            this.ownKills,
            this.ownDeaths,
            this.ownHitsToGo,
            this.otherHitsToGo,
            this.ownKillsToGo,
            this.otherKillsToGo
        );
    }

    public void setScore(Score score){

    }

    public InGameWindow() {
        this.ownHits = 0;
        this.ownHitsRecieved = 0;
        this.ownKills = 0;
        this.ownDeaths = 0;
        this.ownHitsToGo = 18;
        this.ownKillsToGo = 6;
        this.otherHitsToGo = 18;
        this.otherKillsToGo = 6;
        this.infoSelf = new Label("own stats:");
        this.infoOther = new Label("opponent's stats:");
        this.ownHitsText = new Label("your hits: " + this.ownHits);
        this.otherHitsText = new Label("opponent's hits: " + this.ownHitsRecieved);
        this.ownHitsRecievedText = new Label("own hits recieved: " + this.ownHitsRecieved);
        this.otherHitsRecievedText = new Label("opponent's hits recieved: " + this.ownHits);
        this.ownKillsText = new Label("own kills: " + this.ownKills);
        this.otherKillsText = new Label("opponent's kills: " + this.ownDeaths);
        this.ownDeathsText = new Label("own deaths: " + this.ownDeaths);
        this.otherDeathsText = new Label("opponent's deaths: " + this.ownKills);
        this.ownHitsToGoText = new Label("own hits to go: " + this.ownHitsToGo);
        this.otherHitsToGoText = new Label("opponent's hits to go: " + this.otherHitsToGo);
        this.ownKillsToGoText = new Label("own kills to go: " + this.ownKillsToGo);
        this.otherKillsToGoText = new Label("opponent's kills to go: " + this.otherKillsToGo);

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
        this.fire = new Button("Fire!");

        this.setBottom(this.fire);
        this.setLeft(this.ownStats);
        this.setRight(this.otherStats);
    }

    public VBox getOwnStats() {
        return ownStats;
    }

    public void setOwnStats(VBox ownStats) {
        this.ownStats = ownStats;
    }

    public VBox getOtherStats() {
        return otherStats;
    }

    public void setOtherStats(VBox otherStats) {
        this.otherStats = otherStats;
    }

    public Label getOwnHitsText() {
        return ownHitsText;
    }

    public void setOwnHitsText(Label ownHitsText) {
        this.ownHitsText = ownHitsText;
    }

    public Label getOtherHitsText() {
        return otherHitsText;
    }

    public void setOtherHitsText(Label otherHitsText) {
        this.otherHitsText = otherHitsText;
    }

    public Label getOwnHitsRecievedText() {
        return ownHitsRecievedText;
    }

    public void setOwnHitsRecievedText(Label ownHitsRecievedText) {
        this.ownHitsRecievedText = ownHitsRecievedText;
    }

    public Label getOtherHitsRecievedText() {
        return otherHitsRecievedText;
    }

    public void setOtherHitsRecievedText(Label otherHitsRecievedText) {
        this.otherHitsRecievedText = otherHitsRecievedText;
    }

    public Label getOwnKillsText() {
        return ownKillsText;
    }

    public void setOwnKillsText(Label ownKillsText) {
        this.ownKillsText = ownKillsText;
    }

    public Label getOtherKillsText() {
        return otherKillsText;
    }

    public void setOtherKillsText(Label otherKillsText) {
        this.otherKillsText = otherKillsText;
    }

    public Label getOwnDeathsText() {
        return ownDeathsText;
    }

    public void setOwnDeathsText(Label ownDeathsText) {
        this.ownDeathsText = ownDeathsText;
    }

    public Label getOtherDeathsText() {
        return otherDeathsText;
    }

    public void setOtherDeathsText(Label otherDeathsText) {
        this.otherDeathsText = otherDeathsText;
    }

    public Label getOwnHitsToGoText() {
        return ownHitsToGoText;
    }

    public void setOwnHitsToGoText(Label ownHistToGoText) {
        this.ownHitsToGoText = ownHistToGoText;
    }

    public Label getOtherHitsToGoText() {
        return otherHitsToGoText;
    }

    public void setOtherHitsToGoText(Label otherHitsToGoText) {
        this.otherHitsToGoText = otherHitsToGoText;
    }

    public Label getOwnKillsToGoText() {
        return ownKillsToGoText;
    }

    public void setOwnKillsToGoText(Label ownKillsToGoText) {
        this.ownKillsToGoText = ownKillsToGoText;
    }

    public Label getOtherKillsToGoText() {
        return otherKillsToGoText;
    }

    public void setOtherKillsToGoText(Label otherKillsToGoText) {
        this.otherKillsToGoText = otherKillsToGoText;
    }

    public int getOwnHits() {
        return ownHits;
    }

    public void setOwnHits(int ownHits) {
        this.ownHits = ownHits;
        this.ownHitsText.setText("own hits: " + this.ownHits);
        this.otherHitsRecievedText.setText("opponent's hits recieved: " + this.ownHits);
    }

    public int getOwnHitsRecieved() {
        return ownHitsRecieved;
    }

    public void setOwnHitsRecieved(int ownHitsRecieved) {
        this.ownHitsRecieved = ownHitsRecieved;
        this.otherHitsText.setText("opponent's hits: " + this.ownHitsRecieved);
        this.ownHitsRecievedText.setText("own hits recieved: " + this.ownHitsRecieved);
    }

    public int getOwnKills() {
        return ownKills;
    }

    public void setOwnKills(int ownKills) {
        this.ownKills = ownKills;
        this.ownKillsText.setText("own kills: " + this.ownKills);
        this.otherDeathsText.setText("opponent's deaths:" + this.ownKills);
    }

    public int getOwnDeaths() {
        return ownDeaths;
    }

    public void setOwnDeaths(int ownDeaths) {
        this.ownDeaths = ownDeaths;
        this.ownDeathsText.setText("own deaths: " + this.ownDeaths);
        this.otherKillsText.setText("opponent's kills: " + this.ownDeaths);
    }

    public int getOwnHitsToGo() {
        return ownHitsToGo;
    }

    public void setOwnHitsToGo(int ownHitsToGo) {
        this.ownHitsToGo = ownHitsToGo;
        this.ownHitsToGoText.setText("own hits to go: " + this.ownHitsToGo);
    }

    public int getOwnKillsToGo() {
        return ownKillsToGo;
    }

    public void setOwnKillsToGo(int ownKillsToGo) {
        this.ownKillsToGo = ownKillsToGo;
        this.ownKillsToGoText.setText("own kills to go: " + this.ownKillsToGo);
    }

    public Label getInfoSelf() {
        return infoSelf;
    }

    public void setInfoSelf(Label infoSelf) {
        this.infoSelf = infoSelf;
    }

    public Label getInfoOther() {
        return infoOther;
    }

    public void setInfoOther(Label infoOther) {
        this.infoOther = infoOther;
    }

    public int getOtherHitsToGo() {
        return otherHitsToGo;
    }

    public void setOtherHitsToGo(int otherHitsToGo) {
        this.otherHitsToGo = otherHitsToGo;
        this.otherHitsToGoText.setText("opponent's hits to go: " + this.otherHitsToGo);
    }

    public int getOtherKillsToGo() {
        return otherKillsToGo;
    }

    public void setOtherKillsToGo(int otherKillsToGo) {
        this.otherKillsToGo = otherKillsToGo;
        this.otherKillsToGoText.setText("opponent's kills to go: " + this.otherKillsToGo);
    }
}
