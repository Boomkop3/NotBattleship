package com.unplayable.Gui;

import com.unplayable.Static.GlobalVariables;
import com.unplayable.Ship.Rotation;
import com.unplayable.Ship.Ship;
import com.unplayable.Ship.ShipPiece;
import com.unplayable.Static.ResourceReader;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.transform.Affine;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.*;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private Vector2 lastExplosionLocation = new Vector2(0);

    public void createExplosion(int tileX, int tileY){
		Vector2 location;
    	{
			Vector2 GridCoords = getGridCoords(
				tileX, tileY
			);
			location = new Vector2(
				GridCoords.x + (GlobalVariables.shipPieceSize/2),
				GridCoords.y + (GlobalVariables.shipPieceSize/2)
			);
		} // Thanks garbage collector!

		this.lastExplosionLocation = location;

		for (Body body : this.world.getBodies()){
			double proximity = body.getTransform().getTranslation().distance(
				location
			);
			if (proximity > GlobalVariables.explosionDistance){
				continue;
			}
			double forceRatio = 1d - (proximity / GlobalVariables.explosionDistance);
			double angle = location.getAngleBetween(
				body.getTransform().getTranslation()
			);
			body.applyForce(
				new Vector2(angle).multiply(forceRatio*GlobalVariables.explosionForce)
			);
		}
	}

	public void drawGrid(FXGraphics2D g){
		int rows = GlobalVariables.boardWidthHeight;
		int colls = GlobalVariables.boardWidthHeight;
		int tileSize = GlobalVariables.shipPieceSize;
		int boardHeight = rows * tileSize;
		int boardWidth = rows * tileSize;
		g.setColor(
			new Color(100/10*8, 149/10*8, 237/10*8)
		);
		for (int i = 0; i <= colls; i++) {
			g.draw(
				new Line2D.Double(
					i * tileSize,
					0,
					i*tileSize,
					boardHeight
				)
			);
		}
		for (int i = 0; i <= rows; i++) {
			g.draw(
				new Line2D.Double(
					0,
					i * tileSize,
					boardWidth,
					i*tileSize
				)
			);
		}

	}

	private Vector2 getGridCoords(int tileX, int tileY){
    	int tileSize = GlobalVariables.shipPieceSize;
		if (tileX > GlobalVariables.boardWidthHeight || tileY >  GlobalVariables.boardWidthHeight){
			throw new IllegalArgumentException("Tile is not on the playing field");
		}
    	return new Vector2(
    		tileSize * tileX,
			tileSize * tileY
		);
	}

	private void resetRenderArea(FXGraphics2D g){
		g.setBackground(
			new Color(100, 149, 237)
		);
		AffineTransform viewCenter = new AffineTransform();
		int wh = GlobalVariables.boardWidthHeight*GlobalVariables.shipPieceSize;
		viewCenter.translate(
			(this.getWidth()/2)-(wh/2),
			(this.getHeight()/2)-(wh/2)
		);
		g.setTransform(viewCenter);
		g.clearRect(0, 0, wh, wh);
	}

    public void draw(FXGraphics2D g) {
    	resetRenderArea(g);
        drawGrid(g);
        drawShips(g);
        drawDebug(g);
    }

    private void drawShips(FXGraphics2D g){
		for (Ship ship : this.ships){
			ship.draw(g);
		}
		if (GlobalVariables.debug){
			DebugDraw.draw(g, this.world, 1);
		}
	}

    private void drawDebug(FXGraphics2D g){
		new FXGraphics2D(
			this.getGraphicsContext2D()
		).draw(
			new Ellipse2D.Double(
				this.lastExplosionLocation.x,
				this.lastExplosionLocation.y,
				5, 5
			)
		);
	}
}
