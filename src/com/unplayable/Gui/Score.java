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

    public void setHits(int hits) {
        this.hits = hits;
    }

    public void setHitsRecieved(int hitsRecieved) {
        this.hitsRecieved = hitsRecieved;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setOwnHitsToGo(int ownHitsToGo) {
        this.ownHitsToGo = ownHitsToGo;
    }

    public void setOtherHitsToGo(int otherHitsToGo) {
        this.otherHitsToGo = otherHitsToGo;
    }

    public void setOwnKillsToGo(int ownKillsToGo) {
        this.ownKillsToGo = ownKillsToGo;
    }

    public void setOtherKillsToGo(int otherKillsToGo) {
        this.otherKillsToGo = otherKillsToGo;
    }

    public int getHits() {
        return hits;
    }

    public int getHitsRecieved() {
        return hitsRecieved;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getOwnHitsToGo() {
        return ownHitsToGo;
    }

    public int getOtherHitsToGo() {
        return otherHitsToGo;
    }

    public int getOwnKillsToGo() {
        return ownKillsToGo;
    }

    public int getOtherKillsToGo() {
        return otherKillsToGo;
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
