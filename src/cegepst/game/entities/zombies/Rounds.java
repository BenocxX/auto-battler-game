package cegepst.game.entities.zombies;

public enum Rounds {

    ROUND_1(new int[] {4, 3, 2}),
    ROUND_2(new int[] {6, 4, 3}),
    ROUND_3(new int[] {10, 6, 4}),
    ROUND_4(new int[] {10, 7, 6});

    private int[] nbZombies;

    Rounds(int[] nbZombies) {
        this.nbZombies = nbZombies;
    }

    public int[] getNbZombies() {
        return nbZombies;
    }
}
