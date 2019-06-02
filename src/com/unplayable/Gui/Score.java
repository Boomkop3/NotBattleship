package com.unplayable.Gui;

public class Score {
    private int hits;
    private int hitsRecieved;
    private int kills;
    private int deaths;
    private int ownHitsToGo;
    private int otherHitsToGo;
    private int ownKillsToGo;
    private int otherKillsToGo;

    public Score(int hits, int hitsRecieved, int kills, int deaths, int ownHitsToGo, int otherHitsToGo, int ownKillsToGo, int otherKillsToGo) {
        this.hits = hits;
        this.hitsRecieved = hitsRecieved;
        this.kills = kills;
        this.deaths = deaths;
        this.ownHitsToGo = ownHitsToGo;
        this.otherHitsToGo = otherHitsToGo;
        this.ownKillsToGo = ownKillsToGo;
        this.otherKillsToGo = otherKillsToGo;
    }

    public Score getEnemyScore(){
        return new Score(
            this.hitsRecieved,
            this.hits,
            this.deaths,
            this.kills,
            this.otherHitsToGo,
            this.ownHitsToGo,
            this.otherKillsToGo,
            this.ownKillsToGo
        );
    }
}