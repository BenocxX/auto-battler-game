package cegepst.game.eventsystem.events;

public interface TriggerAreaListener {

    void onTriggerEnter(int triggerId);
    void onTrigger(int triggerId);
    void onTriggerLeave(int triggerId);
}
