package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.eventsystem.EventSystem;

public class Item extends StaticEntity {

    private int id;
    protected boolean isBought;

    public Item(int id) {
        this.id = id;
        isBought = false;
    }

    @Override
    public void draw(Buffer buffer) {

    }

    public void buy() {
        isBought = true;
        EventSystem.getInstance().onItemBuy(id);
    }
}
