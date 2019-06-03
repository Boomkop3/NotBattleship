package com.unplayable.Gui.SeaWorld.States;

import com.unplayable.Gui.SeaWorld.SeaWorld;
import com.unplayable.Ship.Ship;
import com.unplayable.Ship.ShipPiece;
import javafx.scene.input.MouseButton;

import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;

public class BoatPlacementState extends SeaWorldState {
	public BoatPlacementState(SeaWorld seaWorld) {
		super(seaWorld);
	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		if (seaWorld.draggedShip != null) {
			Point2D mousePos = seaWorld.getBattleFieldMousePosition(e);
			Point2D position = new Point2D.Double(
				seaWorld.draggedShip.getPosition().getX() + (mousePos.getX() - seaWorld.previousMousePosition.getX()),
				seaWorld.draggedShip.getPosition().getY() + (mousePos.getY() - seaWorld.previousMousePosition.getY()));
			seaWorld.draggedShip.setPosition(position);
			seaWorld.previousMousePosition = new Point2D.Double(mousePos.getX(), mousePos.getY());
		}
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		Point2D mousePos = seaWorld.getBattleFieldMousePosition(e);
		for (Ship ship : seaWorld.ships) {
			for (ShipPiece piece : ship.getPieces()) {
				if (piece.getPosition().x < mousePos.getX()
					&& piece.getPosition().x + 35d > mousePos.getX()
					&& piece.getPosition().y < mousePos.getY()
					&& piece.getPosition().y + 35d > mousePos.getY()) {
					seaWorld.draggedShip = ship;
					if (e.getButton().equals(MouseButton.SECONDARY)) {
						seaWorld.draggedShip.setRotation(
							seaWorld.draggedShip.getRotation().rotateRight()
						);
					}
					seaWorld.previousMousePosition = new Point2D.Double(mousePos.getX(), mousePos.getY());
				}
			}
		}
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		Point2D mousePos = seaWorld.getBattleFieldMousePosition(e);
		mousePos = new Point2D.Double(
			((int)mousePos.getX()/35)*35,
			((int)mousePos.getY()/35)*35
		);
		if (seaWorld.draggedShip != null){
			seaWorld.draggedShip.setPosition(mousePos);
		}
		seaWorld.draggedShip = null;
	}

	@Override
	public void onMouseClicked(MouseEvent e) {

	}

	@Override
	public void handleInput(String message) {

	}

	@Override
	public void update() {

	}
}
