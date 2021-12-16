package cegepst.game.entities.zombies;

public enum Rounds {

    ROUND_1(new int[] {7, 4, 0}),
    ROUND_2(new int[] {10, 6, 1}),
    ROUND_3(new int[] {14, 10, 4}),
    ROUND_4(new int[] {22, 12, 8}),
    ROUND_5(new int[] {22, 12, 8}),
    ROUND_6(new int[] {40, 21, 15}),
    ROUND_7(new int[] {90, 54, 35});

    private int[] nbZombies;

    Rounds(int[] nbZombies) {
        this.nbZombies = nbZombies;
    }

    public int[] getNbZombies() {
        return nbZombies;
    }
}
