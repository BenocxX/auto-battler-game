package cegepst.game.entities.shopPhase;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.eventsystem.EventSystem;

import java.awt.*;

public abstract class Item extends StaticEntity {

    public static final int PRICE = 100;

    private int id;
    protected boolean isBought;
    protected Image image;
    protected Image inventoryImage;
    protected String name;
    protected String description;

    public Item(int id) {
        this.id = id;
        isBought = false;
    }

    // Turn to abstract method
    @Override
    public abstract void draw(Buffer buffer);

    public void buy() {
        isBought = true;
        EventSystem.getInstance().onItemBuy(id);
    }

    public Image getImage() {
        return image;
    }

    public Image getInventoryImage() {
        return inventoryImage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
