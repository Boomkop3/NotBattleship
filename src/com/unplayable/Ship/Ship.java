package com.unplayable.Ship;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Ship {
    private ShipPiece[] pieces;
    private Point2D position;

    public void setPosition(Point2D position){
        this.position = position;
        // ToDo update ship piece positions
    }

    public Ship(String sprite){
        //ToDo split sprite into ship pieces
    }

    public void draw(FXGraphics2D g) throws Exception {
        Arrays.stream(this.pieces).parallel().forEach(piece -> {
            try {
                piece.draw(g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
