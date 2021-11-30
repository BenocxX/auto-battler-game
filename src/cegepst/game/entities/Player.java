package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.eventsystem.events.ButtonListener;
import cegepst.game.settings.GameSettings;
import cegepst.game.resources.Sprite;

import java.awt.*;

public class Player extends ControllableEntity implements ButtonListener {

    private Animator animator;
    private int money;
    private int moneyButtonId;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        teleport(100, 400);
        setSpeed(5);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
        animator.loadAnimations(0, 128, width, height, 3);
        CollidableRepository.getInstance().registerEntity(this);

        EventSystem.getInstance().addButtonListener(this);
        money = 10;
        moneyButtonId = 0;
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
        buffer.drawText(money + " $", 20, RenderingEngine.HEIGHT - 70, Color.WHITE);
        if (hasMoved() && GameSettings.DEBUG_MODE) {
            drawHitBox(buffer);
        }
    }

    @Override
    public void onButtonClick(ButtonEventType eventType) {
        if (ButtonEventType.MONEY_CHEAT == eventType) {
            money += 500;
        }
    }
}
