package cegepst.game.inventory;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.plants.Plant;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;
import java.util.ArrayList;

public class Inventory extends StaticEntity {

    private static Inventory instance;
    private static final int WIDTH = 500;
    private static final int HEIGHT = RenderingEngine.HEIGHT - 65;

    private ArrayList<Slot> slots;

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public void update() {
        for (Slot slot : slots) {
            slot.update();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRoundRectangle(x, y, WIDTH, HEIGHT, 40, 40, new Color(28, 28, 28));
        for (Slot slot : slots) {
            slot.draw(buffer);
        }
    }

    public void unselectEverySlot() {
        for (Slot slot : slots) {
            slot.unselectSlot();
        }
    }

    public void unselectedSelectedSlot() {
        for (Slot slot : slots) {
            if (slot.isSelected()) {
                slot.unselectSlot();
            }
        }
    }

    public void addItem(Plant plant) {
        int y = this.y + 10;
        if (!slots.isEmpty()) {
            y = slots.get(slots.size() - 1).getY() + Slot.HEIGHT + 10;
        }
        slots.add(new Slot(plant,
                CenteringMachine.centerHorizontally(getBounds(), Slot.WIDTH), y));
    }

    public void removeSlot(int index) {
        slots.remove(index);
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    private Inventory() {
        slots = new ArrayList<>();
        Rectangle screenRectangle = new Rectangle(0, 0, RenderingEngine.WIDTH, RenderingEngine.HEIGHT);
        teleport(CenteringMachine.centerHorizontally(screenRectangle, WIDTH),
                CenteringMachine.centerVertically(screenRectangle, HEIGHT));
        setDimension(WIDTH, HEIGHT);
    }
}
