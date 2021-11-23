package cegepst.game.eventsystem;

import cegepst.game.eventsystem.events.ItemBuyListener;
import cegepst.game.eventsystem.events.TriggerAreaListener;

import java.util.ArrayList;

public class EventSystem {

    private static EventSystem instance;

    private ArrayList<TriggerAreaListener> triggerAreaListeners;
    private ArrayList<ItemBuyListener> itemBuyListeners;

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

    private EventSystem() {
        triggerAreaListeners = new ArrayList<>();
        itemBuyListeners = new ArrayList<>();
    }
}
