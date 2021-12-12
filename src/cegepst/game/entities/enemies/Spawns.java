package cegepst.game.entities.enemies;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Spawns {

    SPAWN_1(750, 400),
    SPAWN_2(750, 450),
    SPAWN_3(750, 500);

    private static final List<Spawns> VALUES = Arrays.asList(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    private final int x;
    private final int y;

    Spawns(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Spawns randomSpawn()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
