package cegepst.game.entities.enemies;

public class RunnerZombie extends OldZombie {

    public RunnerZombie() {
        super();
        animator.loadAnimations(192, 128, width, height, 3);
        health = Enemies.RUNNER_ZOMBIE.getHealth();
        damage = Enemies.RUNNER_ZOMBIE.getDamage();
        speed = Enemies.RUNNER_ZOMBIE.getSpeed();
        setSpeed(speed);
    }
}
