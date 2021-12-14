package cegepst.game.helpers;

import cegepst.game.entities.zombies.Round;
import cegepst.game.entities.zombies.Zombie;
import cegepst.game.entities.zombies.Zombies;

import java.util.ArrayList;

public class RoundFactory {

    public static Round getRound(int[] nbZombies) {
        ArrayList<Zombie> zombies = new ArrayList<>();
        addZombies(nbZombies[0], Zombies.FLAG_ZOMBIE, zombies);
        addZombies(nbZombies[1], Zombies.CONE_HEAD_ZOMBIE, zombies);
        addZombies(nbZombies[2], Zombies.BUCKET_HEAD_ZOMBIE, zombies);
        return new Round(zombies);
    }

    private static void addZombies(int nbZombies, Zombies type, ArrayList<Zombie> zombies) {
        for (int i = 0; i < nbZombies; i++) {
            zombies.add(new Zombie(type));
        }
    }
}
