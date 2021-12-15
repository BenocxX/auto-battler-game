package cegepst.game.eventsystem;

import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.zombies.Zombie;
import cegepst.game.eventsystem.events.*;
import cegepst.game.map.Cell;

import java.util.ArrayList;

public class EventSystem {

    private static EventSystem instance;

    private ArrayList<TriggerAreaListener> triggerAreaListeners;
    private ArrayList<ItemBuyListener> itemBuyListeners;
    private ArrayList<ButtonListener> buttonClickListeners;
    private ArrayList<CellListener> cellListeners;
    private ArrayList<SlotListener> slotListeners;
    private ArrayList<SunListener> sunListeners;
    private ArrayList<RoundListener> roundListeners;
    private ArrayList<PlantListener> plantListeners;

    public static EventSystem getInstance() {
        if (instance == null) {
            instance = new EventSystem();
        }
        return instance;
    }

    public void addTriggerAreaListener(TriggerAreaListener listener) {
        triggerAreaListeners.add(listener);
    }

    public void addItemBuyListener(ItemBuyListener listener) {
        itemBuyListeners.add(listener);
    }

    public void addButtonListener(ButtonListener listener) {
        buttonClickListeners.add(listener);
    }

    public void addCellListener(CellListener listener) {
        cellListeners.add(listener);
    }

    public void addSlotListener(SlotListener listener) {
        slotListeners.add(listener);
    }

    public void addSunListener(SunListener listener) {
        sunListeners.add(listener);
    }

    public void addRoundListener(RoundListener listener) {
        roundListeners.add(listener);
    }

    public void addPlantListener(PlantListener listener) {
        plantListeners.add(listener);
    }

    public void onTriggerAreaEnter(int triggerId) {
        for (TriggerAreaListener listener : triggerAreaListeners) {
            listener.onTriggerEnter(triggerId);
        }
    }

    public void onTriggerAreaTriggered(int triggerId) {
        for (TriggerAreaListener listener : triggerAreaListeners) {
            listener.onTrigger(triggerId);
        }
    }

    public void onTriggerAreaLeave(int triggerId) {
        for (TriggerAreaListener listener : triggerAreaListeners) {
            listener.onTriggerLeave(triggerId);
        }
    }

    public void onItemBuy(int itemId) {
        for (ItemBuyListener listener : itemBuyListeners) {
            listener.onItemBuy(itemId);
        }
    }

    public void onButtonClicked(ButtonEventType eventType) {
        for (ButtonListener listener : buttonClickListeners) {
            listener.onButtonClick(eventType);
        }
    }

    public void onCellClick(Cell cell) {
        for (CellListener listener : cellListeners) {
            listener.onCellClick(cell);
        }
    }

    public void onSlotSelection(Plant plant) {
        for (SlotListener listener : slotListeners) {
            listener.onSlotSelection(plant);
        }
    }

    public void onSlotDeselection(Plant plant) {
        for (SlotListener listener : slotListeners) {
            listener.onSlotDeselection(plant);
        }
    }

    public void onSunCreation(Projectile projectile) {
        for (SunListener listener : sunListeners) {
            listener.onSunCreation(projectile);
        }
    }

    public void onZombieSpawn(Zombie zombie) {
        roundListeners.forEach(roundListener -> roundListener.onZombieSpawn(zombie));
    }

    public void onRoundFinished() {
        roundListeners.forEach(RoundListener::onRoundFinished);
    }

    public void onPlantDeath(Plant plant) {
        System.out.println("Calling onPlantDeath event");
        plantListeners.forEach(plantListener -> plantListener.onPlantDeath(plant));
    }

    private EventSystem() {
        triggerAreaListeners = new ArrayList<>();
        itemBuyListeners = new ArrayList<>();
        buttonClickListeners = new ArrayList<>();
        cellListeners = new ArrayList<>();
        slotListeners = new ArrayList<>();
        sunListeners = new ArrayList<>();
        roundListeners = new ArrayList<>();
        plantListeners = new ArrayList<>();
    }
}
