package com.unplayable.Gui;

import com.unplayable.GlobalVariables;
import com.unplayable.Ship.Rotation;
import com.unplayable.Ship.Ship;
import com.unplayable.Ship.ShipPiece;
import com.unplayable.Static.ResourceReader;
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
import org.dyn4j.dynamics.joint.WeldJoint;
import org.dyn4j.geometry.*;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeaWorld extends ResizableCanvas {

    private double deltaTimePassed;
    private double updateRate;
    private World world;
    private List<Ship> ships;

    private List<Ship> getNewShips(World world) {
        int pieceSize = GlobalVariables.shipPieceSize;
		List<Ship> shipCollection = new ArrayList<>();
		try {
			for (String imageFile : ResourceReader.getInstance().getResourceDirectory("/sprites/ships")){
				BufferedImage sprite = ImageIO.read(this.getClass().getResource(imageFile));
				Ship ship = new Ship(sprite, world);
				shipCollection.add(ship);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return shipCollection;
    }

    public SeaWorld(Resizable observer, Parent parent) throws IllegalArgumentException {
        super(observer, parent);
        this.deltaTimePassed = 0;
        this.updateRate = 1000d/60d;
        this.world = new World();
		this.ships = getNewShips(this.world);
		for (int i = 0; i < ships.size(); i++) {
			Ship ship = ships.get(i);
			ship.setPosition(new Point2D.Double((i+1) * 50, 200), Rotation.South);
		}

		ShipPiece bullet = new ShipPiece(null);
		world.addBody(bullet);
		bullet.setPosition(400, 150);
		bullet.applyForce(new Vector2(-10000000, 0));

        world.setGravity(new Vector2(0d, 0d));

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
		for (Ship ship : this.ships){
			ship.draw(g);
		}
		if (GlobalVariables.debug){
			DebugDraw.draw(g, this.world, 1);
		}
    }
}
