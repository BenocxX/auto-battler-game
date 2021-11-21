package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.game.GameSettings;
import cegepst.game.resources.Sound;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ItemBuyListener;
import cegepst.game.eventsystem.events.TriggerAreaListener;

import java.awt.*;

public class BuyStation extends MovableEntity implements TriggerAreaListener, ItemBuyListener {

    private static final Color SELECTED_COLOR = new Color(137, 106, 77);
    private static final Color UNSELECTED_COLOR = new Color(94, 71, 47);

    private Sound sound;
    private Item item;
    private boolean isSelected;
    private boolean hasItem;
    private int id;

    public BuyStation(int x, int y, int id) {
        this.id = id;
        item = new Creature(id, x + 10, y + 10);
        hasItem = true;
        setDimension(30, 30);
        teleport(x, y);
        initializeSound();
        CollidableRepository.getInstance().registerEntity(this);
        EventSystem.getInstance().addTriggerAreaListener(this);
        EventSystem.getInstance().addItemBuyListener(this);
    }

    @Override
    public void draw(Buffer buffer) {
        if (isSelected) {
            buffer.drawRectangle(x, y, width, height, SELECTED_COLOR);
            buffer.drawHorizontallyCenteredText("Buy", getBounds(), y - 5);
            buffer.drawText("(Use E to Buy)", RenderingEngine.WIDTH - 97, 40, Color.WHITE);
        } else {
            buffer.drawRectangle(x, y, width, height, UNSELECTED_COLOR);
        }
        item.draw(buffer);
    }

    @Override
    public void onTriggerEnter(int triggerId) {
        if (id == triggerId && hasItem) {
            isSelected = true;
        }
    }

    @Override
    public void onTrigger(int triggerId) {

    }

    @Override
    public void onTriggerLeave(int triggerId) {
        if (id == triggerId) {
            isSelected = false;
        }
    }

    @Override
    public void onItemBuy(int itemId) {
        if (id == itemId) {
            hasItem = false;
            sound.play(GameSettings.SOUND);
        }
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void buyItem() {
        if (hasItem) {
            item.buy();
        }
    }

    private void initializeSound() {
        sound = Sound.values()[RandomHandler.getInt(2, 5)];
    }
}
