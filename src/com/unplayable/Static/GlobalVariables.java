package com.unplayable.Static;

public class GlobalVariables {
	public static final boolean useObjectStream = false; // false = DataStream // true = ObjectStream
	public static boolean debug = false; // debugging options, including debugdraw

    public static final int shipPieceSize = 35; // Grid size, don't change this. I don't think it's properly implemented
    public static final int boardWidthHeight = 12; // The number of tiles on each side of the playing field
    public static final int explosionDistance = 60; // the distance of the blast radius
    public static final int explosionLethalDistance = 45; // The distance at which the blast radius causes damage to ships
    public static final int explosionForce = 100000; // The ammount of force the ships are hit with

	// Commands used to communicate over the network, completely arbitary
    public static final String startGameCommand = "startgameplz";
    public static final String ImReadyCommand = "yoImReady";
    public static final String waitStateCommand = "HoStopRightThere";
    public static final String ClickToFireStateCommand = "PewPew";
    public static final String WaitForAttackCommand = "GoGetFknRekt";
    public static final String PlayerWinCommand = "YouGotEmTiger";
    public static final String PlayerLossCommand = "YougotRekM8";

    public static final String fireModeText = "Shoot at someone";
    public static final String waitForDamageModeText = "Get shot at";
}
