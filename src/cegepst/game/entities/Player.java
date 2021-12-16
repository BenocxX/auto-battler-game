package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.controls.MovementController;
import cegepst.engine.entities.ControllableEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.eventsystem.events.ButtonListener;
import cegepst.game.settings.GameSettings;
import cegepst.game.resources.Sprite;

public class Player extends ControllableEntity
        implements ButtonListener {

    private static final int INITIAL_MONEY = 2000;

    private Animator animator;
    private int money;
    private boolean inBattle = false;

    public Player(MovementController controller) {
        super(controller);
        setDimension(32, 32);
        teleport(100, 400);
        setSpeed(5);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
        animator.loadAnimations(0, 128, width, height, 3);
        CollidableRepository.getInstance().registerEntity(this);

        EventSystem.getInstance().addButtonListener(this);
        money = INITIAL_MONEY;
    }

    @Override
    public void update() {
        if (!inBattle) {
            super.update();
            moveAccordingToController();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (!inBattle) {
            buffer.drawImage(animator.getImage(getDirection()), x ,y);
            buffer.drawHorizontallyCenteredText(money + " $", getBounds(), y - 10);
            if (hasMoved() && GameSettings.DEBUG_MODE) {
                drawHitBox(buffer);
            }
        }
    }

    @Override
    public void onButtonClick(ButtonEventType eventType) {
        if (eventType == ButtonEventType.MONEY_CHEAT) {
            money += 500;
        }
    }

    public boolean canBuy(int price) {
        return money >= price;
    }

    public void buyItem(int price) {
        money -= price;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void setInBattle(boolean inBattle) {
        if (inBattle) {
            CollidableRepository.getInstance().unregisterEntity(this);
        } else {
            CollidableRepository.getInstance().registerEntity(this);
        }
        this.inBattle = inBattle;
    }
}
