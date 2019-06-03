package com.unplayable.Gui.SeaWorld.States;

import com.unplayable.Gui.GameStateView;
import com.unplayable.Gui.PlayWindow;
import com.unplayable.Gui.Score;
import com.unplayable.Gui.SeaWorld.SeaWorld;

import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;

import java.awt.geom.Point2D;
import java.io.IOException;

public class ClickToFireState extends SeaWorldState {
	public ClickToFireState(SeaWorld seaWorld) {
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
		System.out.println("click");
		Point2D mousePosition = this.seaWorld.getBattleFieldMousePosition(e);
		int x = (int)mousePosition.getX()/35;
		int y = (int)mousePosition.getY()/35;
		try {
			((PlayWindow)this.seaWorld.getParent()).getConnection().writeString("hit:"+x+":"+y);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.seaWorld.setState(
			new WaitForAttackState(this.seaWorld)
		);
	}

	@Override
	public void handleInput(String message) {

	}

	@Override
	public void update() {
		this.seaWorld.update();
	}
}
