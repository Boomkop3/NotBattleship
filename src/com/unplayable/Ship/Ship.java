package com.unplayable.Ship;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public abstract class Ship {
    private ShipPiece[] pieces;
    private Point2D position;
    private double rotation;

    public void setPosition(Point2D position, Rotation rotation){
        this.position = position;
        ShipPiece[] shipPieces = this.pieces;
        int verticalOffset = 0;
        int horizontalOffset = 0;
        switch (rotation) {
            case North: {
                verticalOffset = 1;
                break;
            }
            case South: {
                verticalOffset = -1;
                break;
            }
            case East: {
                horizontalOffset = 1;
                break;
            }
            case West: {
                horizontalOffset = -1;
                break;
            }
        }
        for (int i = 0, shipPiecesLength = shipPieces.length; i < shipPiecesLength; i++) {
            ShipPiece piece = shipPieces[i];
            int size = piece.getImage().getHeight();
            piece.setPosition(
                new Point2D.Double(
                    (int)this.position.getX() + (i * horizontalOffset),
                    (int)this.position.getY() + (i * verticalOffset)
                )
            );
        }
    }

    public Ship(String sprite){
        //ToDo split sprite into ship pieces
    }

    public void draw(FXGraphics2D g) {
        Arrays.stream(this.pieces).parallel().forEach(piece -> {
            try {
                piece.draw(g);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
