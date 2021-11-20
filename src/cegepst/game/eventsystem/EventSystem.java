package cegepst.game.eventsystem;

import java.util.ArrayList;

public class EventSystem {

    private static EventSystem instance;

    // Arraylist des Interface TriggerAreaListener
    private ArrayList<TriggerAreaListener> triggerAreaListeners;

    /**
     * Permet d'obtenir l'instance du EventSystem
     */
    public static EventSystem getInstance() {
        if (instance == null) {
            instance = new EventSystem();
        }
        return instance;
    }

    /**
     * Cette fonction permet d'ajouter de nouveaux triggerAreaListeners à
     * la liste.
     */
    public void addTriggerAreaListener(TriggerAreaListener listener) {
        triggerAreaListeners.add(listener);
    }

    /**
     * Cette fonction sert à looper aux travers de toutes les interfaces
     * de la liste et de call leur méthode appropriée. On passe en
     * paramètre le id du trigger ayant déclenché se call afin
     * de permettre à l'objet se faisant déclencher de vérifier qu'il
     * est bien relié au bon déclencheur.
     */
    public void onTriggerAreaEnter(int triggerId) {
        for (TriggerAreaListener listener : triggerAreaListeners) {
            listener.onTriggerEnter(triggerId);
        }
    }

    /**
     * Cette fonction sert à looper aux travers de toutes les interfaces
     * de la liste et de call leur méthode appropriée. On passe en
     * paramètre le id du trigger ayant déclenché se call afin
     * de permettre à l'objet se faisant déclencher de vérifier qu'il
     * est bien relié au bon déclencheur.
     */
    public void onTriggerAreaTriggered(int triggerId) {
        for (TriggerAreaListener listener : triggerAreaListeners) {
            listener.onTrigger(triggerId);
        }
    }

    /**
     * Cette fonction sert à looper aux travers de toutes les interfaces
     * de la liste et de call leur méthode appropriée. On passe en
     * paramètre le id du trigger ayant déclenché se call afin
     * de permettre à l'objet se faisant déclencher de vérifier qu'il
     * est bien relié au bon déclencheur.
     */
    public void onTriggerAreaLeave(int triggerId) {
        for (TriggerAreaListener listener : triggerAreaListeners) {
            listener.onTriggerLeave(triggerId);
        }
    }

    /**
     * Le constructor qui instancie les listes de listeners.
     */
    private EventSystem() {
        triggerAreaListeners = new ArrayList<>();
    }
}
