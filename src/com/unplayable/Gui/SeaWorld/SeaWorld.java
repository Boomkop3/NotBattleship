package com.unplayable.Gui.SeaWorld;

import com.unplayable.Gui.DebugDraw;
import com.unplayable.Gui.Score;
import com.unplayable.Gui.SeaWorld.States.SeaWorldState;
import com.unplayable.Gui.SeaWorld.States.WaitingState;
import com.unplayable.Ship.ShipPiece;
import com.unplayable.Static.GlobalVariables;
import com.unplayable.Ship.Ship;
import com.unplayable.Static.ResourceReader;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
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
    public List<Ship> ships;
    public Ship draggedShip = null;
    public Point2D previousMousePosition;
    private double deltaTimePassed;
    private double updateRate;
	private World world;
    private Vector2 lastExplosionLocation = new Vector2(0);
    private SeaWorldState state;

    public Score getScore(){
    	return new Score(
    		0,
			this.getDestroyedPieceCount(),
			0,
			this.getKillCount(),
			-1,
			this.getPieceCount() - this.getDestroyedPieceCount(),
			-1,
			this.getShipCount() - this.getKillCount()
		);
	}

	public int getShipCount(){
    	return this.ships.size();
	}

	public int getPieceCount(){
    	int pieceCount = 0;
		for (Ship ship : this.ships){
			pieceCount += ship.getPieces().length;
		}
		return pieceCount;
	}

	public int getDestroyedPieceCount(){
		int destroyed = 0;
		for (Ship ship : this.ships){
			for (ShipPiece piece : ship.getPieces()){
				if (piece.isDestroyed()){
					destroyed++;
				}
			}
		}
		return destroyed;
	}

	public int getKillCount(){
		int kills = 0;
		for (Ship ship : this.ships){
			if (ship.isDead()){
				kills++;
			}
		}
		return kills;
	}

    public SeaWorld(Resizable observer, Parent parent) throws IllegalArgumentException {
        super(observer, parent);
        this.setState(
        	new WaitingState(this)
		);
        this.setEvents();
        this.deltaTimePassed = 0;
        this.updateRate = 1000d/60d;
        this.world = new World();
        this.addWalls();
		this.ships = getNewShips(this.world);
		for (int i = 0; i < ships.size(); i++) {
			Ship ship = ships.get(i);
			ship.setPosition(new Point2D.Double((i+1) * 50, 200));
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

	private void setEvents() {
		this.setOnMouseDragged(this::onMouseDragged);
		this.setOnMousePressed(this::onMousePressed);
		this.setOnMouseReleased(this::onMouseReleased);
		this.setOnMouseClicked(this::onMouseClicked);
	}

	public void setState(SeaWorldState state){
    	this.state = state;
	}

	private void onMouseClicked(MouseEvent mouseEvent) {
    	this.state.onMouseClicked(mouseEvent);
	}

	private void addWall(boolean vertical, int x, int y){
		Body body = new Body();
		if (vertical){
			body.addFixture(
				new Rectangle(1, 1000)
			);
		}
		else {
			body.addFixture(
				new Rectangle(1000, 1)
			);
		}
		body.setMass(MassType.INFINITE);
		body.getTransform().setTranslation(x, y);
		world.addBody(body);
	}

	private void addWalls(){
    	int boardSize = GlobalVariables.shipPieceSize*GlobalVariables.boardWidthHeight;
    	this.addWall(true, 0, 0);
    	this.addWall(true, boardSize, 0);
    	this.addWall(false, 0, 0);
    	this.addWall(false, 0, boardSize);
	}

    private List<Ship> getNewShips(World world) {
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

	public Point2D getBattleFieldMousePosition(MouseEvent e){
		return getBattleFieldMousePosition(new Point2D.Double(e.getX(), e.getY()));
	}

    private Point2D getBattleFieldMousePosition(double x, double y){
    	return getBattleFieldMousePosition(new Point2D.Double(x, y));
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

	private Point2D getTile(Point2D coordinate){
    	return new Point2D.Double(
			(int)coordinate.getX() / GlobalVariables.shipPieceSize,
			(int)coordinate.getY() / GlobalVariables.shipPieceSize
		);
	}

	private void onMouseDragged(MouseEvent e){
		this.state.onMouseDragged(e);
	}

	private void onMousePressed(MouseEvent e){
    	this.state.onMousePressed(e);
	}

	private void onMouseReleased(MouseEvent e){
		this.state.onMouseReleased(e);
	}


    public void update(double deltaTimeMillis){
    	Thread.yield();
        this.deltaTimePassed += deltaTimeMillis;
        if (deltaTimePassed > updateRate){
            deltaTimePassed = deltaTimePassed - updateRate;
            this.state.update();
        }
    }

	public void update(){
		this.world.update(this.updateRate);
    }

    public void createExplosion(int tileX, int tileY){
		Vector2 location;
    	try {
			Vector2 GridCoords = getGridCoords(
				tileX, tileY
			);
			location = new Vector2(
				GridCoords.x + (GlobalVariables.shipPieceSize/2),
				GridCoords.y + (GlobalVariables.shipPieceSize/2)
			);
			// Thanks garbage collector!
		} catch (IllegalArgumentException e){
    		return;
		}

		this.lastExplosionLocation = location;

		for (Ship ship : this.ships){
			for (ShipPiece body : ship.getPieces()){
				Vector2 bodyLocation = body.getTransform().getTranslation();
				double maxDistance = GlobalVariables.explosionDistance;
				double proximity = bodyLocation.distance(
					location
				);
				if (proximity > maxDistance){
					continue;
				}
				if (proximity < GlobalVariables.explosionLethalDistance){
					if (body instanceof ShipPiece){
						((ShipPiece)body).setIsDestroyed(true);
					}
				}
				Vector2 force = new Vector2(
					maxDistance / (bodyLocation.x - location.x) * GlobalVariables.explosionForce,
					maxDistance / (bodyLocation.y - location.y) * GlobalVariables.explosionForce
				);
				body.applyForce(
					force
				);
			}
		}
	}

	private void drawGrid(FXGraphics2D g){
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
		g.setTransform(new AffineTransform());
		g.setBackground(
			Color.white
		);
		g.clearRect(0, 0, (int)this.getWidth(), (int)this.getHeight());
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
    	Thread.yield();
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

	public void handleInput(String message) {
    	this.state.handleInput(message);
	}
}
