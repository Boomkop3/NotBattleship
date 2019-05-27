package com.unplayable.Gui;

import com.unplayable.GlobalVariables;
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
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class SeaWorld extends ResizableCanvas {

    private double deltaTimePassed;
    private double updateRate;
    private World world;
    private Ship[] ships;

    private Ship[] getNewShips(World world) throws IOException {
        int pieceSize = GlobalVariables.shipPieceSize;
        Ship[] shipCollection = new Ship[10];
        for (String imageFile : ResourceReader.getInstance().getResourceDirectory("/sprites/ships")){
            BufferedImage shipImage = ImageIO.read(this.getClass().getResource(imageFile));
            int shipPieceCount = shipImage.getHeight()/pieceSize;
            BufferedImage[] shipPieceImages = new BufferedImage[shipPieceCount];
            for (int i = 0; i < shipPieceCount; i++) {
                shipPieceImages
            }

        }

        return shipCollection;
    }

    public SeaWorld(Resizable observer, Parent parent) throws IllegalArgumentException {
        super(observer, parent);
        this.deltaTimePassed = 0;
        this.updateRate = 1000d/60d;
        this.world = new World();
        try {
            this.ships = getNewShips(this.world);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        //DebugDraw.draw(g, this.world, 1);
        for (Body body : this.world.getBodies()){
            ((ShipPiece)body).draw(g);
        }
    }
}
