package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.triggers.Triggerable;
import cegepst.game.GameSettings;
import cegepst.game.Sound;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.TriggerAreaListener;

import java.awt.*;

public class BuyStation extends MovableEntity implements Triggerable, TriggerAreaListener {

    private Sound sound;
    private Friend friendForSell;
    private boolean isSelected;
    private boolean isBought;
    private int id;

    public BuyStation(int x, int y, int id) {
        this.id = id;
        EventSystem.getInstance().addTriggerAreaListener(this);
        setDimension(30, 30);
        teleport(x, y);
        friendForSell = new Friend(x + 10, y + 10);
        CollidableRepository.getInstance().registerEntity(this);
        initializeSound();
    }

    @Override
    public void draw(Buffer buffer) {
        if (isSelected) {
            buffer.drawRectangle(x, y, width, height, new Color(137, 106, 77));
            if (!isBought) {
                buffer.drawText("Buy", x + 5, y - 5, new Color(255, 255, 255));
                buffer.drawText("(Use E to Buy)", RenderingEngine.WIDTH - 97, 40, new Color(255, 255, 255));
            }
        } else {
            buffer.drawRectangle(x, y, width, height, new Color(94, 71, 47));
        }
        if (!isBought) {
            friendForSell.draw(buffer);
        }
    }

    @Override
    public void onTriggerEnter(int triggerId) {
        if (id == triggerId) {
            isSelected = !isBought;
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

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void buy() {
        if (!isBought) {
            isBought = true;
            sound.play(GameSettings.SOUND);
        }
    }

    private void initializeSound() {
        int randomSound = RandomHandler.getInt(4);
        if (randomSound == 0) {
            sound = Sound.BUY_1;
        } else if (randomSound == 1) {
            sound = Sound.BUY_2;
        } else if (randomSound == 2) {
            sound = Sound.BUY_3;
        } else {
            sound = Sound.BUY_4;
        }
    }

    @Override
    public void trigger() {
        isSelected = !isBought;
    }

    @Override
    public void untrigger() {
        isSelected = false;
    }
}
