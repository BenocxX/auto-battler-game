package cegepst.game.map;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.PlantOld;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.PlantListener;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;

public class Cell extends StaticEntity implements PlantListener {

    public final static int WIDTH = 55;
    public final static int HEIGHT = 90;

    private TriggerArea area;
    private boolean isClicked;
    private boolean isEmpty;
    private Plant currentPlant;

    public Cell(int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        area = new TriggerArea(x, y, width, height);
        isEmpty = true;
        EventSystem.getInstance().addPlantListener(this);
    }

    @Override
    public void draw(Buffer buffer) {
        area.draw(buffer);
    }

    @Override
    public void onPlantDeath(Plant plant) {
        if (currentPlant == plant) {
            currentPlant = null;
            empty();
        }
    }

    public boolean isClicked(Point mousePosition) {
        isClicked = getBounds().contains(mousePosition);
        if (isClicked) {
            EventSystem.getInstance().onCellClick(this);
        }
        return isClicked;
    }

    public void placeEntity(StaticEntity entity) {
        isEmpty = false;
        Rectangle areaRectangle = new Rectangle(getX(), getY(), area.getWidth(), area.getHeight());
        entity.teleport(CenteringMachine.centerHorizontally(areaRectangle, entity.getWidth()),
                CenteringMachine.centerVertically(areaRectangle, entity.getHeight()));
    }

    public void addPlant(Plant plant) {
        currentPlant = plant;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void empty() {
        isEmpty = true;
    }
}
