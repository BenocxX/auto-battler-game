package cegepst.game.eventsystem.events;

import cegepst.game.entities.plants.Plant;

public interface PlantListener {

    void onPlantDeath(Plant plant);
}
