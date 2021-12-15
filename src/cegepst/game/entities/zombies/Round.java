package cegepst.game.entities.zombies;

import cegepst.engine.helpers.RandomHandler;
import cegepst.game.eventsystem.EventSystem;

import java.util.ArrayList;

public class Round {

    private final static int START_COOLDOWN = 900; // 900
//    private final static int MIN_COOLDOWN = 60;
//    private final static int MAX_COOLDOWN = 180;
    private final static int MIN_COOLDOWN = 20;
    private final static int MAX_COOLDOWN = 60;

    private ArrayList<Zombie> zombies;
    private int spawnCoolDown;
    private int roundCooldown;

    public Round(ArrayList<Zombie> zombies) {
        this.zombies = zombies;
        roundCooldown = START_COOLDOWN;
        spawnCoolDown = 0;
    }

    public void update() {
        roundCooldown--;
        if (roundCooldown <= 0) {
            roundCooldown = 0;
            spawnCoolDown--;
            if (spawnCoolDown <= 0 && zombies.size() > 0) {
                EventSystem.getInstance().onZombieSpawn(getRandomZombie());
                spawnCoolDown = RandomHandler.getInt(MIN_COOLDOWN, MAX_COOLDOWN);
            } else if (zombies.size() < 1) {
                EventSystem.getInstance().onRoundFinished();
            }
        }
    }

    private Zombie getRandomZombie() {
        Zombie zombie;
        if (zombies.size() > 1) {
            zombie = zombies.get(RandomHandler.getInt(zombies.size() -1));
        } else {
            zombie = zombies.get(0);
        }
        zombies.remove(zombie);
        return zombie;
    }
}
