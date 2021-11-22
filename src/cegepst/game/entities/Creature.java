package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.RandomHandler;
import cegepst.game.resources.Sprite;

import java.awt.*;

public class Creature extends Item {

    private static final int WIDTH = 44;
    private static final int HEIGHT = 44;

    private CreatureType creatureType;
    private Image sprite;

    public Creature(int id, int x, int y) {
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
