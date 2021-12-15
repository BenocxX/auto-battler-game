package cegepst.game.eventsystem.events;

import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.PlantOld;

public interface PlantListener {

    void onPlantDeath(Plant plant);
}
