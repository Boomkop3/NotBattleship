package com.unplayable.Gui;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class WindowTwo extends BorderPane {
    private VBox ownStats;
    private VBox otherStats;
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
    private Label ownHistToGoText;
    private Label otherHitsToGoText;
    private int ownKillsToGo;
    private Label ownKillsToGoText;
    private Label otherKillsToGoText;

    public WindowTwo() {
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

    public Label getOwnHistToGoText() {
        return ownHistToGoText;
    }

    public void setOwnHistToGoText(Label ownHistToGoText) {
        this.ownHistToGoText = ownHistToGoText;
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
    }

    public int getOwnHitsRecieved() {
        return ownHitsRecieved;
    }

    public void setOwnHitsRecieved(int ownHitsRecieved) {
        this.ownHitsRecieved = ownHitsRecieved;
    }

    public int getOwnKills() {
        return ownKills;
    }

    public void setOwnKills(int ownKills) {
        this.ownKills = ownKills;
    }

    public int getOwnDeaths() {
        return ownDeaths;
    }

    public void setOwnDeaths(int ownDeaths) {
        this.ownDeaths = ownDeaths;
    }

    public int getOwnHitsToGo() {
        return ownHitsToGo;
    }

    public void setOwnHitsToGo(int ownHitsToGo) {
        this.ownHitsToGo = ownHitsToGo;
    }

    public int getOwnKillsToGo() {
        return ownKillsToGo;
    }

    public void setOwnKillsToGo(int ownKillsToGo) {
        this.ownKillsToGo = ownKillsToGo;
    }

}
