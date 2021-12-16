package cegepst.game.eventsystem.events;

import cegepst.game.entities.zombies.Zombie;

public interface ZombieListener {

    void onZombieSpawn(Zombie zombie);
}
