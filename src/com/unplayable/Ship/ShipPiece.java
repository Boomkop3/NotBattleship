package com.unplayable.Ship;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ShipPiece {
    private boolean destroyed;
    private BufferedImage image;
    private Point2D position;

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void draw() {
        //ToDo draw piece, with/withouth explosion

        // Draw base layer
        if (this.destroyed){
            //add explosion
        }
    }

    private void setPosition(Point2D position) {
        this.position = position;
    }

    public ShipPiece(BufferedImage image){
        this.image = image;
    }
}
