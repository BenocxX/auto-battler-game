package cegepst.game.eventsystem;

import cegepst.game.entities.shopPhase.CreatureType;
import cegepst.game.eventsystem.events.*;

import java.util.ArrayList;

public class EventSystem {

    private static EventSystem instance;

    private ArrayList<TriggerAreaListener> triggerAreaListeners;
    private ArrayList<ItemBuyListener> itemBuyListeners;
    private ArrayList<ButtonListener> buttonClickListeners;
    private ArrayList<MorphListener> morphListeners;
    private ArrayList<PlayerAttackListener> playerAttackListeners;

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

    public void addMorphListener(MorphListener listener) {
        morphListeners.add(listener);
    }

    public void addPlayerAttackListener(PlayerAttackListener listener) {
        playerAttackListeners.add(listener);
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

    public void onMorph(CreatureType creatureType) {
        for (MorphListener listener : morphListeners) {
            listener.onMorph(creatureType);
        }
    }

    public void onTargetAttack(int id, int damage) {
        for (PlayerAttackListener listener : playerAttackListeners) {
            listener.onTargetAttack(id, damage);
        }
    }

    public void onAreaAttack(int[] id, int damage) {
        for (PlayerAttackListener listener : playerAttackListeners) {
            listener.onAreaAttack(id, damage);
        }
    }

    private EventSystem() {
        triggerAreaListeners = new ArrayList<>();
        itemBuyListeners = new ArrayList<>();
        buttonClickListeners = new ArrayList<>();
        morphListeners = new ArrayList<>();
        playerAttackListeners = new ArrayList<>();
    }
}
