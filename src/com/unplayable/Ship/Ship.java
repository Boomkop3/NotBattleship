package com.unplayable.Ship;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public abstract class Ship {
    private ShipPiece[] pieces;
    private Point2D position;

    public void setPosition(Point2D position){
        this.position = position;
        // ToDo update ship piece positions
    }

    public Ship(String sprite){
        //ToDo split sprite into ship pieces
    }

    public void draw(){
        Arrays.stream(this.pieces).parallel().forEach(piece -> piece.draw());
    }
}
