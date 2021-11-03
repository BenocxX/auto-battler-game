package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;

public class Player extends ControllableEntity {

    public Player(MovementController controller) {
        super(controller);
        setDimension(30, 50);
        teleport(250, 250);
        setSpeed(5);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {

    }
}
