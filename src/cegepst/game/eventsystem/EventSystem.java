package cegepst.game.eventsystem;

import cegepst.game.entities.plants.Plant;
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

    private EventSystem() {
        triggerAreaListeners = new ArrayList<>();
        itemBuyListeners = new ArrayList<>();
        buttonClickListeners = new ArrayList<>();
        cellListeners = new ArrayList<>();
        slotListeners = new ArrayList<>();
    }
}
