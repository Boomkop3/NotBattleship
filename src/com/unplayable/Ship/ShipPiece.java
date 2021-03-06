package com.unplayable.Ship;

import com.unplayable.Static.GlobalVariables;
import com.unplayable.Static.ImageLibrary;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class ShipPiece extends Body {
    private boolean destroyed;
    private BufferedImage image;
    private float width;
    private float height;

    public ShipPiece(BufferedImage image){
        this.width = 35;
        this.height = 35;
        this.image = image;
        // dyn4j
        this.setAngularDamping(0.75);
        this.setLinearDamping(0.75);
        this.addFixture(
            new Rectangle(this.width, this.height)
        );
        this.setMass(MassType.NORMAL);
        this.setMass(
            new Mass(new Vector2(0, 0), 1, 1000)
        );
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setPosition(Point2D position){
        this.setPosition(position.getX(), position.getY());
    }

    public void setPosition(double x, double y){
        this.getTransform().setTranslation(
        	x + (GlobalVariables.shipPieceSize / 2),
			y + (GlobalVariables.shipPieceSize / 2));
    }

    public Vector2 getPosition() {
        Vector2 position = this.getTransform().getTranslation();
        return new Vector2(
                position.x - (GlobalVariables.shipPieceSize / 2),
                position.y - (GlobalVariables.shipPieceSize / 2)
        );
    }

	public void setIsDestroyed(boolean destroyed) {
    	this.destroyed = destroyed;
	}
}
