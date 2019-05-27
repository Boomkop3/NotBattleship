package com.unplayable.Gui;

import com.unplayable.Ship.Ship;
import com.unplayable.Ship.ShipPiece;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.shape.Shape;
import org.dyn4j.collision.AbstractBounds;
import org.dyn4j.collision.Bounds;
import org.dyn4j.collision.Collidable;
import org.dyn4j.collision.Fixture;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SeaWorld extends ResizableCanvas {

    private double deltaTimePassed;
    private double updateRate;
    private World world;
    private Ship[] ships;

    private Ship[] getNewShips(){
        Ship[] shipCollection = new Ship[10];
        // ToDo add template ship collection
        return shipCollection;
    }

    public SeaWorld(Resizable observer, Parent parent) throws IllegalArgumentException {
        super(observer, parent);
        this.deltaTimePassed = 0;
        this.updateRate = 1000d/60d;
        this.ships = getNewShips();
        this.world = new World();
        world.setGravity(new Vector2(0d, 0d));

        ShipPiece piece = new ShipPiece(null);
        this.world.addBody(
            piece
        );

        // debugging
        piece.applyForce(
            new Vector2(1000, 1000)
        );
        piece.applyTorque(12);

        FXGraphics2D graphics = new FXGraphics2D(this.getGraphicsContext2D());

        new AnimationTimer(){
            long last = -1;
            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000.0d);
                last = now;
                try {
                    draw(graphics);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void update(double deltaTimeMillis){
        this.deltaTimePassed += deltaTimeMillis;
        if (deltaTimePassed > updateRate){
            deltaTimePassed = deltaTimePassed - updateRate;
            this.update();
        }
    }

    private void update(){
        this.world.update(this.updateRate);
    }

    public void draw(FXGraphics2D g) throws Exception{
        g.setBackground(
            new Color(100, 149, 237)
        );
        g.clearRect(0, 0, (int)this.getWidth(), (int)this.getHeight());
        for (Body body : this.world.getBodies()){
            ((ShipPiece)body).draw(g);
        }
    }
}
