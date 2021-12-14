package cegepst.game.eventsystem.events;

import cegepst.game.entities.plants.Plant;

public interface SlotListener {

    void onSlotSelection(Plant plant);
    void onSlotDeselection(Plant plant);
}
