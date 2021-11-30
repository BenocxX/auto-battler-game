package cegepst.game.entities.shopPhase;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.inventory.Inventory;
import cegepst.game.inventory.Slot;

import java.awt.*;

public class CreatureItem extends Item {

    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;

    private CreatureType creatureType;

    public CreatureItem(int id, int x, int y) {
        super(id);
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        creatureType = getRandomCreatureType();
        image = creatureType.getSprite(WIDTH, HEIGHT);
        inventoryImage = creatureType.getSprite(Slot.IMAGE_WIDTH, Slot.IMAGE_HEIGHT);
        name = creatureType.getName();
        description = "Description goes here";
        Inventory.getInstance().addItem(this);
    }

    @Override
    public void draw(Buffer buffer) {
        if (!isBought) {
            buffer.drawImage(image, x, y);
        }
    }

    private CreatureType getRandomCreatureType() {
        return CreatureType.values()[RandomHandler.getInt(CreatureType.values().length)];
    }
}
