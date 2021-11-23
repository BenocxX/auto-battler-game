package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.RandomHandler;
import cegepst.game.CreatureType;

import java.awt.*;

public class CreatureItem extends Item {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;

    private CreatureType creatureType;
    private Image sprite;

    public CreatureItem(int id, int x, int y) {
        super(id);
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        creatureType = getRandomCreatureType();
        sprite = creatureType.getSprite(WIDTH, HEIGHT);
    }

    @Override
    public void draw(Buffer buffer) {
        if (!isBought) {
            buffer.drawImage(sprite, x, y);
        }
    }

    public String name() {
        return creatureType.getName();
    }

    private CreatureType getRandomCreatureType() {
        return CreatureType.values()[RandomHandler.getInt(CreatureType.values().length)];
    }
}
