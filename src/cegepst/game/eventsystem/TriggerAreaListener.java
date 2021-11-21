package cegepst.game.eventsystem;

public interface TriggerAreaListener {

    void onTriggerEnter(int triggerId);
    void onTrigger(int triggerId);
    void onTriggerLeave(int triggerId);
}
