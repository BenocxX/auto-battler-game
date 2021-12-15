package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;

public class PlantSelector extends StaticEntity {

    private Plant plant;

    public PlantSelector(Plant plant) {
        setDimension(plant.getWidth(), plant.getHeight());
        teleport(CenteringMachine.centerHorizontally(new Rectangle(125, 200), getBounds()), 100);
        plant.teleport(x, y);
        this.plant = plant;
    }

    @Override
    public void draw(Buffer buffer) {
        plant.draw(buffer);
    }

    public Plant getPlant() {
        return plant.getPlantOfSameType();
    }
}
