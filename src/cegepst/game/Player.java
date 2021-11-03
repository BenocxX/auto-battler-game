package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;

public class Player extends ControllableEntity {

    public Player(MovementController controller) {
        super(controller);
    }

    @Override
    public void draw(Buffer buffer) {

    }
}
