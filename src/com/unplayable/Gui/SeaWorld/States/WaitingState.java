package com.unplayable.Gui.SeaWorld.States;

import com.unplayable.Gui.SeaWorld.SeaWorld;

import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

public class WaitingState extends SeaWorldState {

	public WaitingState(SeaWorld seaWorld) {
		super(seaWorld);
	}

	@Override
	public void onMouseDragged(MouseEvent e) {

	}

	@Override
	public void onMousePressed(MouseEvent e) {

	}

	@Override
	public void onMouseReleased(MouseEvent e) {

	}

	@Override
	public void onMouseClicked(MouseEvent e) {

	}

	@Override
	public void handleInput(String message) {

	}

	@Override
	public void update() {
		seaWorld.update();
	}
}
