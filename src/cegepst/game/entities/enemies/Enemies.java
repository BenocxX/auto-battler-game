package cegepst.game.entities.enemies;

public enum Enemies {

    ZOMBIE(100, 10, 1),
    RUNNER_ZOMBIE(60, 5, 3);

    private int health;
    private int damage;
    private int speed;

    Enemies(int health, int damage, int speed) {
       this.health = health;
       this.damage = damage;
       this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }
}
