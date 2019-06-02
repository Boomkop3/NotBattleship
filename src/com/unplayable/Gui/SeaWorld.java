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
	private boolean InGame;
    private double deltaTimePassed;
    private double updateRate;
    private World world;
    private List<Ship> ships;
    private Ship dragged = null;
    private Point2D previousPosition;

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

    private Point2D getBattleFieldMousePosition(Point2D point){
    	double battlefieldSize = GlobalVariables.shipPieceSize * GlobalVariables.boardWidthHeight;
    	double offsetX = (this.getWidth() - battlefieldSize)/2;
    	double offsetY = (this.getHeight() - battlefieldSize)/2;
    	return new Point2D.Double(
				point.getX() - offsetX,
				point.getY() - offsetY
		);
	}

	private Vector2 getTile(Point2D coordinate){
    	return new Vector2(
			(int)coordinate.getX() / GlobalVariables.shipPieceSize,
			(int)coordinate.getY() / GlobalVariables.shipPieceSize
		);
	}

    public SeaWorld(Resizable observer, Parent parent) throws IllegalArgumentException {
        super(observer, parent);
        this.setOnMousePressed((event -> {
        	Point2D _mousePos = new Point2D.Double(event.getX(), event.getY());
        	Point2D mousePos = getBattleFieldMousePosition(_mousePos);
			createExplosion((int)mousePos.getX()/GlobalVariables.shipPieceSize, (int)mousePos.getY()/GlobalVariables.shipPieceSize);
        	if (!InGame) {
				System.out.println("X: " + mousePos.getX() + " Y: " + mousePos.getY());
				for (Ship ship : ships) {
					if (ship.getPosition().x < mousePos.getX() && ship.getPosition().x + 35d > mousePos.getX() && ship.getPosition().y < mousePos.getY() && ship.getPosition().y + 35 * ship.getPieces().length > mousePos.getY()) {
						System.out.println("ship selected");
						this.dragged = ship;
						System.out.println("XShip: " + this.dragged.getPosition().x + " YShip: " + this.dragged.getPosition().y);
						this.previousPosition = new Point2D.Double(mousePos.getX(), mousePos.getY());
					}
				}
			}
				})
		);
        this.setOnMouseDragged(event -> {
        	if (!InGame) {
        		if (dragged != null) {
					Point2D _mousePos = new Point2D.Double(event.getX(), event.getY());
					Point2D mousePos = getBattleFieldMousePosition(_mousePos);
					Point2D position = new Point2D.Double(
							this.dragged.getPosition().x + (mousePos.getX() - this.previousPosition.getX()),
							this.dragged.getPosition().y + (mousePos.getY() - this.previousPosition.getY()));
        			this.dragged.setPosition(position, this.dragged.getRotation());
					this.previousPosition = new Point2D.Double(mousePos.getX(), mousePos.getY());
				}

        	}
		});
        this.setOnMouseReleased(event -> {
        	if (!InGame) {
        		this.dragged = null;
			}
		});
        this.InGame = false;
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
    	if (this.InGame) {
			this.world.update(this.updateRate);
		} else {
		}
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
			Vector2 bodyLocation = body.getTransform().getTranslation();
			double maxDistance = GlobalVariables.explosionDistance;
			double proximity = bodyLocation.distance(
				location
			);
			if (proximity > maxDistance){
				continue;
			}
			double forceRatio = 1d - (proximity / maxDistance);
			/* Always results in the same direction
			double angle = location.getAngleBetween(
				body.getTransform().getTranslation()
			);
			body.applyForce(
				new Vector2(angle).multiply(forceRatio*GlobalVariables.explosionForce)
			);
			*/
			Vector2 force = new Vector2(
				maxDistance / (bodyLocation.x - location.x) * GlobalVariables.explosionForce,
				maxDistance / (bodyLocation.y - location.y) * GlobalVariables.explosionForce
			);
			body.applyForce(
				force
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

	private Vector2 getGridCoords(Vector2 point){
    	return getGridCoords((int)point.x, (int)point.x);
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
    	if (!GlobalVariables.debug){
    		return;
		}
    	// Draw last Explosion
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

	public boolean isInGame() {
		return InGame;
	}

	public void setInGame(boolean inGame) {
		InGame = inGame;
	}
}
