package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class PlantSelector extends StaticEntity {

    private final static Color UNSELECTED_COLOR = new Color(0, 0, 0, 0);
    private final static Color SELECTED_COLOR = new Color(0, 0, 0, 50);

    private Plant plant;
    private boolean isSelected = false;
    private boolean isClicked;

    public PlantSelector(int x, int y, Plant plant) {
        setDimension(plant.getWidth(), plant.getHeight());
        teleport(x, y);
        this.plant = plant;
    }

    public boolean isClicked(Point mousePosition) {
        isClicked = getBounds().contains(mousePosition);
        if (isClicked) {
            isSelected = !isSelected;
        }
        return isClicked;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isSelected) {
            buffer.drawRectangle(x, y, plant.getWidth(), plant.getHeight(), SELECTED_COLOR);
        } else {
            buffer.drawRectangle(x, y, plant.getWidth(), plant.getHeight(), UNSELECTED_COLOR);
        }
        plant.draw(buffer);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Plant getPlant() {
        return plant.getPlantOfSameType();
    }
}
