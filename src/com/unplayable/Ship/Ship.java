package com.unplayable.Ship;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Ship {
    private ShipPiece[] pieces;
    private Point2D position;
    private double rotation;

    public void setPosition(Point2D position, Rotation rotation){
        this.position = position;
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
        for (int i = 0, shipPiecesLength = this.pieces.length; i < shipPiecesLength; i++) {
            ShipPiece piece = this.pieces[i];
            int size = piece.getImage().getHeight();
            piece.setPosition(
                new Point2D.Double(
                    (int)this.position.getX() + (i * horizontalOffset),
                    (int)this.position.getY() + (i * verticalOffset)
                )
            );
        }
    }

    public Ship(BufferedImage sprite) {
        // Ship sprites are stored horizontally
        int size = sprite.getWidth();
        this.pieces = new ShipPiece[sprite.getHeight()/size];
        for (int i = 0; i < pieces.length; i++) {
            this.pieces[i] = new ShipPiece(sprite.getSubimage(i * size, 0, size, size));
        }
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
