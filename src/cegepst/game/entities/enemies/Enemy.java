package cegepst.game.entities.enemies;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.entities.Killable;
import cegepst.game.resources.Sprite;

import java.util.Random;

public abstract class Enemy extends MovableEntity implements Killable {

    protected Animator animator;
    protected int id;
    protected int health;
    protected int damage;
    protected int speed;

    public Enemy() {
        Spawns spawn = Spawns.randomSpawn();
        teleport(spawn.getX(), spawn.getY());
        setDimension(32, 32);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
        id = (new Random()).nextInt(5000);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int dealDamage() {
        return damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isColliding(StaticEntity entity) {
        return hitBoxIntersectWith(entity) && !isDead();
    }

    public int getId() {
        return id;
    }
}
