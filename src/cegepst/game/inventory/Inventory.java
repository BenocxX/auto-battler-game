package cegepst.game.inventory;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.shopPhase.Item;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;
import java.util.ArrayList;

public class Inventory extends StaticEntity {

    private static Inventory instance;
    private static final int WIDTH = 500;
    private static final int HEIGHT = RenderingEngine.HEIGHT - 50;

    private ArrayList<Slot> slots;

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, WIDTH, HEIGHT, new Color(255, 0, 0));
        for (Slot slot : slots) {
            slot.draw(buffer);
        }
    }

    public void addItem(Item item) {
        int y = this.y + 10;
        if (!slots.isEmpty()) {
            y = slots.get(slots.size() - 1).getY() + Slot.HEIGHT + 10;
        }
        slots.add(new Slot(item,
                CenteringMachine.centerHorizontally(getBounds(), Slot.WIDTH), y));
    }

    public void removeSlot(int index) {
        slots.remove(index);
    }

    private Inventory() {
        slots = new ArrayList<>();
        Rectangle screenRectangle = new Rectangle(0, 0, RenderingEngine.WIDTH, RenderingEngine.HEIGHT);
        teleport(CenteringMachine.centerHorizontally(screenRectangle, WIDTH),
                CenteringMachine.centerVertically(screenRectangle, HEIGHT));
        setDimension(WIDTH, HEIGHT);
    }
}
