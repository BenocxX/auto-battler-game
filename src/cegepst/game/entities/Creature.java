package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.RandomHandler;

public class Creature extends Item {

    private CreatureType creatureType;

    public Creature(int id, int x, int y) {
        super(id);
        setDimension(10, 10);
        teleport(x, y);
        creatureType = getRandomCreatureType();
    }

    @Override
    public void draw(Buffer buffer) {
        if (!isBought) {
            buffer.drawRectangle(x, y, width, height, creatureType.getColor());
        }
    }

    public String name() {
        return creatureType.getName();
    }

    private CreatureType getRandomCreatureType() {
        return CreatureType.values()[RandomHandler.getInt(CreatureType.values().length)];
    }
}
