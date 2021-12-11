package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.entities.shopPhase.CreatureType;
import cegepst.game.entities.shopPhase.Item;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.eventsystem.events.ButtonListener;
import cegepst.game.eventsystem.events.MorphListener;
import cegepst.game.helpers.CenteringMachine;
import cegepst.game.settings.GameSettings;
import cegepst.game.resources.Sprite;

import java.awt.*;

public class Player extends ControllableEntity
        implements MorphListener, ButtonListener, Killable {

    private static final int INITIAL_MONEY = 500;

    private Animator animator;
    private Image morphSprite;
    private int money;
    private int health;
    private int damage;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        teleport(100, 400);
        setSpeed(5);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
        animator.loadAnimations(0, 128, width, height, 3);
        CollidableRepository.getInstance().registerEntity(this);

        EventSystem.getInstance().addMorphListener(this);
        EventSystem.getInstance().addButtonListener(this);
        money = INITIAL_MONEY;
        health = 100;
        damage = 10;
    }

    @Override
    public void update() {
        super.update();
        moveAccordingToController();
        if (morphSprite == null) {
            animator.updateAnimationFrame(hasMoved());
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (morphSprite == null) {
            buffer.drawImage(animator.getImage(getDirection()), x ,y);
        } else {
            buffer.drawImage(morphSprite, x ,y);
        }
        buffer.drawHorizontallyCenteredText("HP: " + health, getBounds(), y - 10);
        buffer.drawText(money + " $", 20, RenderingEngine.HEIGHT - 70, Color.WHITE);
        if (hasMoved() && GameSettings.DEBUG_MODE) {
            drawHitBox(buffer);
        }
    }

    @Override
    public void onButtonClick(ButtonEventType eventType) {
        if (eventType == ButtonEventType.MONEY_CHEAT) {
            money += 500;
        }
    }

    @Override
    public void onMorph(CreatureType creatureType) {
        setDimension(50, 50);
        morphSprite = creatureType.getSprite(50, 50);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int dealDamage() {
        return damage;
    }

    public boolean canBuy() {
        return money >= Item.PRICE;
    }

    public void buyItem() {
        money -= Item.PRICE;
    }
}
