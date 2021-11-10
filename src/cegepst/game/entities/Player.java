package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.game.GameSettings;
import cegepst.game.triggers.TriggerRepository;
import cegepst.game.triggers.Triggerer;

import java.awt.*;

public class Player extends ControllableEntity implements Triggerer {

    public Player(MovementController controller) {
        super(controller);
        setDimension(30, 50);
        teleport(250, 250);
        setSpeed(5);
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(32, 170, 32));
        if (hasMoved() && GameSettings.DEBUG_MODE) {
            drawHitBox(buffer);
        }
    }

    @Override
    public void isTriggering(TriggerRepository repository) {
        repository.triggerTriggerables(this);
    }
}
