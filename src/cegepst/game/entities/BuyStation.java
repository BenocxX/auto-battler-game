package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.game.GameSettings;
import cegepst.game.Sound;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.TriggerAreaListener;

import java.awt.*;

public class BuyStation extends MovableEntity implements TriggerAreaListener {

    private static final Color SELECTED_COLOR = new Color(137, 106, 77);
    private static final Color UNSELECTED_COLOR = new Color(94, 71, 47);

    private Sound sound;
    private Creature creature;
    private boolean isSelected;
    private int id;

    public BuyStation(int x, int y, int id) {
        this.id = id;
        EventSystem.getInstance().addTriggerAreaListener(this);
        setDimension(30, 30);
        teleport(x, y);
        creature = new Creature(x + 10, y + 10);
        CollidableRepository.getInstance().registerEntity(this);
        initializeSound();
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
        creature.draw(buffer);
    }

    @Override
    public void onTriggerEnter(int triggerId) {
        if (id == triggerId && !creature.isBought()) {
            isSelected = true;
        }
    }

    @Override
    public void onTrigger(int triggerId) { }

    @Override
    public void onTriggerLeave(int triggerId) {
        if (id == triggerId) {
            isSelected = false;
        }
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void buy() {
        if (!creature.isBought()) {
            creature.buy();
            sound.play(GameSettings.SOUND);
        }
    }

    private void initializeSound() {
        sound = Sound.values()[RandomHandler.getInt(2, 5)];
    }
}
