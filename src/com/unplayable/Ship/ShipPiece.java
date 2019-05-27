package com.unplayable.Ship;

import com.unplayable.Static.ImageLibrary;
import javafx.scene.shape.Shape;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Vector2;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ShipPiece extends Body {
    private boolean destroyed;
    private BufferedImage image;

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void draw(FXGraphics2D g) throws Exception {
        System.out.println(this.getTransform());
        g.draw(
            new Ellipse2D.Double(
                (int)this.getTransform().getTranslationX(), this.getTransform().getTranslationY(), 25, 25
            )
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

    public void setPosition(Point2D position) {

    }

    public ShipPiece(BufferedImage image){
        this.image = image;
    }
}
