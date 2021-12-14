package cegepst.game.entities.zombies;

import cegepst.engine.helpers.RandomHandler;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.map.Cell;

import java.util.ArrayList;

public class Round {

//    private final static int MIN_COOLDOWN = 60;
//    private final static int MAX_COOLDOWN = 180;
    private final static int MIN_COOLDOWN = 20;
    private final static int MAX_COOLDOWN = 60;

    private ArrayList<Zombie> zombies;
    private ArrayList<Cell> spawningCells;
    private int cooldown;

    public Round(ArrayList<Cell> spawningCells, ArrayList<Zombie> zombies) {
        this.spawningCells = spawningCells;
        this.zombies = zombies;
        teleportZombiesOnRandomSpawningCell();
//        cooldown = 360;
        cooldown = 0;
    }

    public void update() {
        cooldown--;
        if (cooldown <= 0 && zombies.size() > 0) {
            EventSystem.getInstance().onZombieSpawn(getRandomZombie());
            cooldown = RandomHandler.getInt(MIN_COOLDOWN, MAX_COOLDOWN);
        }
    }

    private void teleportZombiesOnRandomSpawningCell() {
        zombies.forEach(zombie -> {
            getRandomSpawningCell().placeEntity(zombie);
        });
    }

    private Cell getRandomSpawningCell() {
        return spawningCells.get(RandomHandler.getInt(spawningCells.size()));
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
