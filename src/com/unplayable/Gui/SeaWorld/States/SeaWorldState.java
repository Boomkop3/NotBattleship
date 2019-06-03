package com.unplayable.Gui.SeaWorld.States;

import com.unplayable.Gui.SeaWorld.SeaWorld;
import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

public abstract class SeaWorldState {
	public SeaWorld seaWorld;
	public abstract void onMouseDragged(MouseEvent e);
	public abstract void onMousePressed(MouseEvent e);
	public abstract void onMouseReleased(MouseEvent e);
	public abstract void onMouseClicked(MouseEvent e);
	public abstract void handleInput(String message);
	public abstract void update();
	public SeaWorldState(SeaWorld seaWorld){
		this.seaWorld = seaWorld;
	}
}
