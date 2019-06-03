package com.unplayable.Gui.SeaWorld.States;

import com.unplayable.Gui.GameStateView;
import com.unplayable.Gui.PlayWindow;
import com.unplayable.Gui.Score;
import com.unplayable.Gui.SeaWorld.SeaWorld;
import javafx.scene.input.MouseEvent;

public class GettingHitState extends SeaWorldState {

    public GettingHitState(SeaWorld seaWorld) {
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
        if (message.startsWith("igothit:")){
            String[] messages = message.split(":");
            int enemyDestroyedPieceCount = Integer.valueOf(messages[1]);
            int enemyDeathCount = Integer.valueOf(messages[2]);
            GameStateView gameStateView = ((GameStateView)((PlayWindow)this.seaWorld.getParent()).getLeft());
            Score newScore = gameStateView.getScore();
            newScore.setKills(enemyDeathCount);
            newScore.setHits(enemyDestroyedPieceCount);
            gameStateView.setScore(newScore);
            gameStateView.updateInterface();
            this.seaWorld.setState(
                new WaitForAttackState(this.seaWorld)
            );
        }
    }

    @Override
    public void update() {

    }
}
