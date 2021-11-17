package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.GameSettings;
import cegepst.engine.triggers.TriggerRepository;
import cegepst.engine.triggers.Triggerer;
import cegepst.game.Sprite;

public class Player extends ControllableEntity implements Triggerer {

    // TODO: Faire un hashMap: HashMap<StaticEntity, Animator>
    private Animator animator;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        teleport(250, 250);
        setSpeed(5);

        animator = new Animator(
                Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
        animator.loadAnimations(0, 128, width, height, 3);

        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
        animator.updateAnimationFrame(hasMoved());
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(animator.getImage(getDirection()), x ,y);
        if (hasMoved() && GameSettings.DEBUG_MODE) {
            drawHitBox(buffer);
        }
    }

    @Override
    public void isTriggering(TriggerRepository repository) {
        repository.triggerTriggerables(this);
    }
}
