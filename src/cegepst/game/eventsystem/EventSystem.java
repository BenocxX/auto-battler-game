package cegepst.game.eventsystem;

import cegepst.game.eventsystem.events.CreatureBuyListener;
import cegepst.game.eventsystem.events.TriggerAreaListener;

import java.util.ArrayList;

public class EventSystem {

    /** TODO: QUESTION
     *  Question #1:
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
     *
     *
     *  Question #2:
     *  Lorsque mon jeu va être bien avancé, je risque d'avoir beaucoup
     *  d'événements différents. Est-ce que ce système sera toujours bon?
     *  J'ai en tête qu'il est "expendable", mais je me questionne tout de
     *  même à ce sujet puisque je vais devoir ajouter pleins d'ArrayList,
     *  pleins d'interfaces et plein de methode pour call les listener.
     *
     *  Il y aurait-t-il un moyen de refactor cette classe pour avoir une
     *  seule ArrayList de "EventListener" qui pourrait ping tous les
     *  eventListeners écoutant un event lorsque celui-ci est call?
     *  Pour ce faire, toutes les interfaces d'Events seraient des enfants
     *  d'un interface parent afin de faire du polymorphism.
     *
     *  J'aurais une interface mère "EventListener" et plusieurs interfaces
     *  enfants qui implémenteraient "EventListener". L'interface mère aurait
     *  une methode par défaut qui serait call a chaque fois qu'une interface
     *  enfant serait call. La methode par défaut call 'EventSystem.getInstance().onEvent();'
     *  Voir: https://stackoverflow.com/questions/19976487/explicitly-calling-a-default-method-in-java
     *
     *  Ex:
     *  private ArrayList<EventListener> eventListeners;
     *
     *  public void addEventListeners(EventListener eventListener) {
     *      eventListeners.add(eventListener);
     *  }
     *
     *  public void onEvent(int id) {
     *      for (EventListener eventListener : eventListeners) {
     *          eventListener.event();
     *      }
     *  }
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
