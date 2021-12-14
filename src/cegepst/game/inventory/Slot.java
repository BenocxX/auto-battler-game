package cegepst.game.inventory;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.plants.Plant;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;

public class Slot extends StaticEntity {

    public static final int WIDTH = 400;
    public static final int HEIGHT = 100;
    public static final int IMAGE_WIDTH = 75;
    public static final int IMAGE_HEIGHT = 75;

    private MousePad mousePad;
    private MenuSystem menuSystem;
    private Plant plant;

    public Slot(Plant plant, int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        mousePad = new MousePad();
        menuSystem = new MenuSystem();
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.selectPlantButton(x + 10 + IMAGE_WIDTH + 10, y + 60, plant));
        this.plant = plant;
    }

    public void update() {
        menuSystem.update();
        mousePad.resetClickedButtons();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRoundRectangle(x, y, WIDTH, HEIGHT, 40, 40, new Color(49, 49, 49));
        buffer.drawImage(plant.getImage(), x + 10,
                CenteringMachine.centerVertically(getBounds(), IMAGE_HEIGHT),
                IMAGE_WIDTH, IMAGE_HEIGHT);
        buffer.drawText(plant.getName(), x + 10 + IMAGE_WIDTH + 10, y + 25, Color.WHITE);
        menuSystem.draw(buffer);
    }
}
