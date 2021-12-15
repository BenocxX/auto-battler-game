package cegepst.game.eventsystem.events;

import cegepst.game.entities.zombies.Zombie;

public interface RoundListener {

    void onZombieSpawn(Zombie zombie);
    void onRoundFinished();
}
