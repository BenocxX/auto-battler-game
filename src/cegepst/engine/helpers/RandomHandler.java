package cegepst.engine.helpers;

import java.util.Random;

public class RandomHandler {

    private static Random random = new Random();

    public static int getInt(int max) {
        return random.nextInt(max);
    }
}
