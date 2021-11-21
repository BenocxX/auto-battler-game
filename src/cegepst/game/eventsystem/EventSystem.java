package cegepst.game.eventsystem;

import cegepst.game.eventsystem.events.CreatureBuyListener;
import cegepst.game.eventsystem.events.TriggerAreaListener;

import java.util.ArrayList;

public class EventSystem {

    /** TODO: QUESTION
     *  J'ai créé un event system afin de pouvoir call des méthodes d'objets
     *  suite à un déclenchement venant d'un autre classe. Ce moyen contre
     *  l'utilisation de multiples getters/setters et évite le passage
     *  d'objet en paramètre.
     *
     *  Cependant, est-ce que ça brise l'encapsulation? Étant donné que je
     *  peux call une méthode d'un objet sans avoir besoin de son instance..?
     *
     *  J'ai quelques difficultés à voir si c'est correct. Genre ça me semble
     *  correct, mais je trouve ça louche en même temps.
     */

    private static EventSystem instance;

    private ArrayList<TriggerAreaListener> triggerAreaListeners;
    private ArrayList<CreatureBuyListener> creatureBuyListener;

    public static EventSystem getInstance() {
        if (instance == null) {
            instance = new EventSystem();
        }
        return instance;
    }

    public void addTriggerAreaListener(TriggerAreaListener listener) {
        triggerAreaListeners.add(listener);
    }

    public void addCreatureBuyListener(CreatureBuyListener listener) {
        creatureBuyListener.add(listener);
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

    public void onCreatureBuy(int creatureId) {
        for (CreatureBuyListener listener : creatureBuyListener) {
            listener.onBuy(creatureId);
        }
    }

    private EventSystem() {
        triggerAreaListeners = new ArrayList<>();
        creatureBuyListener = new ArrayList<>();
    }
}
