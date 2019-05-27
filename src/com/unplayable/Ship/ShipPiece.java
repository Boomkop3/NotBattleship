package com.unplayable.Ship;

import com.unplayable.Static.ImageLibrary;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.*;
import org.dyn4j.geometry.Rectangle;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ShipPiece extends Body {
    private boolean destroyed;
    private BufferedImage image;
    private float width;
    private float height;

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void draw(FXGraphics2D g) throws Exception {
        Shape shape = new Rectangle2D.Double(
            0, 0, this.width/2d, this.height/2d
        );
        AffineTransform at = new AffineTransform();
        at.translate(
            this.getTransform().getTranslationX(),
            this.getTransform().getTranslationY()
        );
        at.rotate(this.getTransform().getRotation());
        g.draw(
            at.createTransformedShape(shape)
        );

        /*
        AffineTransform at = new AffineTransform();
        g.drawImage(this.image, (int)this.position.getX(), (int)this.position.getY(), null);
        // Draw base layer
        if (this.destroyed){
            g.drawImage(
                ImageLibrary.getInstance().SHIP_PIECE_DESTROYED_OVERLAY,
                (int)this.position.getX(), (int)this.position.getY(), null);
        }
        */
    }

    public ShipPiece(BufferedImage image){
        this.width = 35;
        this.height = 35;
        this.image = image;
        // dyn4j
        this.setAngularDamping(1);
        this.setLinearDamping(1);
        this.addFixture(
            new Rectangle(this.width, this.height)
        );
        this.setMass(MassType.NORMAL);
        this.setMass(
            new Mass(new Vector2(this.width/2d, this.height/2d), 1, 1)
        );
    }
}
