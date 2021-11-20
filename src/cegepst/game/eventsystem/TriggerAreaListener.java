package cegepst.game.eventsystem;

public interface TriggerAreaListener {
    /**
     * Appelée lorsque le trigger est entré.
     */
    void onTriggerEnter(int triggerId);

    /**
     * Appelée durant que le trigger est déclenché.
     */
    void onTrigger(int triggerId);

    /**
     * Appelée lorsque que le trigger est quitté.
     */
    void onTriggerLeave(int triggerId);
}
