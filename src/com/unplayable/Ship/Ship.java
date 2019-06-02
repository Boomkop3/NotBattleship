package com.unplayable.Ship;

import com.unplayable.Static.GlobalVariables;
import com.unplayable.Static.ImageLibrary;
import org.dyn4j.dynamics.World;
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Ship {
    private ShipPiece[] pieces;
    private BufferedImage sprite;

    public void setPosition(int x, int y, Rotation rotation){
    	this.setPosition(new Point2D.Double(x, y), rotation);
	}

    public void setPosition(Point2D position, Rotation rotation){
        for (int i = 0, shipPiecesLength = this.pieces.length; i < shipPiecesLength; i++) {
            ShipPiece piece = this.pieces[i];
            int size = piece.getImage().getHeight();
            piece.setPosition(
                new Point2D.Double(
                    (int)position.getX() + (i * rotation.offset.getX() * size),
                    (int)position.getY() + (i * rotation.offset.getY() * size)
                )
            );
        }
    }

    public Ship(BufferedImage sprite, World world) {
        // Ship sprites are stored horizontally
		this.sprite = sprite;
        int size = sprite.getWidth();
        this.pieces = new ShipPiece[sprite.getHeight()/size];
        for (int i = 0; i < pieces.length; i++) {
			this.pieces[i] = (new ShipPiece(sprite.getSubimage(0, i * size, size, size)));
		}
		this.setPosition(0, 0, Rotation.South);
		for (int i = 0; i < pieces.length; i++) {
			world.addBody(pieces[i]);
			if (i > 0){
				WeldJoint weld = new WeldJoint(pieces[i-1], pieces[i], new Vector2());
				world.addJoint(weld);
			}
		}
    }

	public int getCenterPieceIndex(){
    	return this.pieces.length/2;
	}

    public ShipPiece getCenterPiece(){
    	return this.pieces[this.getCenterPieceIndex()];
	}

    public void draw(FXGraphics2D g){
    	int centerIndex = this.getCenterPieceIndex();
    	int size = GlobalVariables.shipPieceSize;
    	ShipPiece centerPiece = this.getCenterPiece();

		AffineTransform origin = g.getTransform();
		AffineTransform at = new AffineTransform();
		at.setTransform(origin);
		at.translate(centerPiece.getTransform().getTranslationX(), centerPiece.getTransform().getTranslationY());
		at.rotate(centerPiece.getTransform().getRotation());
		g.setColor(Color.RED);
		g.setTransform(at);
        g.drawImage(
        	this.sprite, -size/2, -size/2-(size*centerIndex), null
		);
		ShipPiece[] pieces1 = this.pieces;
		for (int i = 0; i < pieces1.length; i++) {
			ShipPiece piece = pieces1[i];
			if (piece.isDestroyed() || GlobalVariables.debug) {
				g.drawImage(
					ImageLibrary.getInstance().SHIP_PIECE_DESTROYED_OVERLAY, -size/2, -size/2+(size*(i-centerIndex)), null
				);
			}
		}
		g.setColor(Color.BLACK);
		g.setTransform(origin);
    }
}
