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
    private Rotation rotation;

    public void setRotation(Rotation rotation){
    	this.rotation = rotation;
    	this.setPosition(
    		this.getPosition()
		);
	}

	public ShipPiece[] getPieces() {
		return pieces;
	}

	public void setPosition(int x, int y){
    	this.setPosition(new Point2D.Double(x, y));
	}

    public void setPosition(Point2D position){
		position = new Point2D.Double(
			position.getX() + (GlobalVariables.shipPieceSize/2),
			position.getY() + (GlobalVariables.shipPieceSize/2)
		);
        for (int i = 0, shipPiecesLength = this.pieces.length; i < shipPiecesLength; i++) {
            ShipPiece piece = this.pieces[i];
            int size = piece.getImage().getHeight();
            piece.setPosition(
                new Point2D.Double(
                    (int)position.getX() + (i * rotation.offset.getX() * size),
                    (int)position.getY() + (i * rotation.offset.getY() * size)
                )
            );
            piece.getTransform().setRotation(this.rotation.getTheta());
        }
    }

	public Point2D getPosition(){
		Vector2 position = this.pieces[0].getTransform().getTranslation();
		return new Point2D.Double(
			position.x - (GlobalVariables.shipPieceSize/2),
			position.y - (GlobalVariables.shipPieceSize/2)
		);
	}

    public Ship(BufferedImage sprite, World world) {
        // Ship sprites are stored horizontally
		this.sprite = sprite;
		this.rotation = Rotation.South;
        int size = sprite.getWidth();
        this.pieces = new ShipPiece[sprite.getHeight()/size];
        for (int i = 0; i < pieces.length; i++) {
			this.pieces[i] = (new ShipPiece(sprite.getSubimage(0, i * size, size, size)));
		}
		this.setPosition(0, 0);
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
    	//ShipPiece centerPiece = this.getCenterPiece();
		ShipPiece firstPiece = this.pieces[0];
		AffineTransform origin = g.getTransform();
		AffineTransform at = new AffineTransform();
		at.setTransform(origin);
		at.translate(firstPiece.getTransform().getTranslationX(), firstPiece.getTransform().getTranslationY());
		at.rotate(firstPiece.getTransform().getRotation());
		g.setColor(Color.RED);
		g.setTransform(at);
        g.drawImage(
        	this.sprite, -size/2, -size/2-(size*(pieces.length-1)), null
		);
		ShipPiece[] pieces1 = this.pieces;
		for (int i = 0; i < pieces1.length; i++) {
			ShipPiece piece = pieces1[i];
			if (piece.isDestroyed() || GlobalVariables.debug) {
				g.drawImage(
					ImageLibrary.getInstance().SHIP_PIECE_DESTROYED_OVERLAY, -size/2, -size/2+(size*i)-(size*(pieces.length-1)), null
				);
			}
		}
		g.setColor(Color.BLACK);
		g.setTransform(origin);
    }

	public Rotation getRotation() {
		return rotation;
	}
}
