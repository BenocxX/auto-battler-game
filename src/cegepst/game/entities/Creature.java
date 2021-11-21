package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.game.eventsystem.EventSystem;

import java.awt.*;

public class Creature extends MovableEntity {

    private Color color;
    private int id;
    private boolean isBought;

    public Creature(int id, int x, int y) {
        this.id = id;
        setDimension(10, 10);
        teleport(x, y);
        color = new Color(RandomHandler.getInt(100, 255), RandomHandler.getInt(100, 255), RandomHandler.getInt(100, 255));
        isBought = false;
    }

    @Override
    public void draw(Buffer buffer) {
        if (!isBought) {
            buffer.drawRectangle(x, y, width, height, color);
        }
    }

    public void buy() {
        isBought = true;
        EventSystem.getInstance().onCreatureBuy(id);
    }
}
