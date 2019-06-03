package com.unplayable.Gui.SeaWorld.States;

import com.unplayable.Gui.PlayWindow;
import com.unplayable.Gui.SeaWorld.SeaWorld;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class WaitForAttackState extends SeaWorldState {
	public WaitForAttackState(SeaWorld seaWorld) {
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
		System.out.println("Recieved message: ");
		System.out.println(message);
		if (message.startsWith("hit:")){
			String[] coords = message.split(":");
			int x = Integer.valueOf(coords[1]);
			int y = Integer.valueOf(coords[2]);
			this.seaWorld.createExplosion(x, y);
			try {
				((PlayWindow)this.seaWorld.getParent()).getConnection().writeString(
					"igothit:"
						+ this.seaWorld.getDestroyedPieceCount()
						+ ":"
						+ this.seaWorld.getKillCount()
				);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.seaWorld.setState(
				new ClickToFireState(this.seaWorld)
			);
		}
	}

	@Override
	public void update() {
		this.seaWorld.update();
	}
}
