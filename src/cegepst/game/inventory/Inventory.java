package cegepst.game.inventory;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.PlantOld;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;
import java.util.ArrayList;

public class Inventory extends StaticEntity {

    private static Inventory instance;
    private static final int WIDTH = 500;
    private static final int HEIGHT = RenderingEngine.HEIGHT - 65;

    private Rectangle screenRectangle;
    private ArrayList<Slot> slots;
    private int currentPage;
    private int offset = 0;
    private int limit = 4;
    private int nextY;

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
        buffer.drawHorizontallyCenteredText("Page " + currentPage, screenRectangle, RenderingEngine.HEIGHT - 70);
        for (int i = offset; i < limit + offset; i++) {
            if (i < slots.size()) {
                slots.get(i).draw(buffer);
            }
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
        slots.add(new Slot(plant,
                CenteringMachine.centerHorizontally(getBounds(), Slot.WIDTH), nextY));
        nextY += Slot.HEIGHT + 10;
        if (nextY >= 440) {
            resetSlotY();
        }
    }

    public void previousPage() {
        if (offset - 4 >= 0) {
            currentPage--;
            offset -= 4;
        }
    }

    public void nextPage() {
        if (offset + 4 < slots.size()) {
            currentPage++;
            offset += 4;
        }
    }

    public void removeSlot(int index) {
        slots.remove(index);
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public void clear() {
        slots.clear();
    }

    private void resetSlotY() {
        nextY = this.y + 10;
    }

    private Inventory() {
        slots = new ArrayList<>();
        screenRectangle = new Rectangle(0, 0, RenderingEngine.WIDTH, RenderingEngine.HEIGHT);
        teleport(CenteringMachine.centerHorizontally(screenRectangle, WIDTH),
                CenteringMachine.centerVertically(screenRectangle, HEIGHT));
        setDimension(WIDTH, HEIGHT);
        resetSlotY();
        currentPage = 1;
    }
}
