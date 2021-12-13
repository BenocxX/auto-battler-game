package cegepst.game.entities.enemies;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.entities.Killable;
import cegepst.game.entities.Player;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.PlayerAttackListener;
import cegepst.game.resources.Sprite;

import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy extends MovableEntity implements Killable, PlayerAttackListener {

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
        EventSystem.getInstance().addPlayerAttackListener(this);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int dealDamage() {
        return damage;
    }

    @Override
    public void onTargetAttack(Enemy enemy, int damage) {
        if (this == enemy) {
            takeDamage(damage);
        }
    }

    @Override
    public void onRowAttack(ArrayList<Enemy> enemies, int damage) {
        for (Enemy enemy : enemies) {
            enemy.takeDamage(damage);
        }
    }

    @Override
    public void onAreaAttack(ArrayList<Enemy> enemies, int damage) {
        for (Enemy enemy : enemies) {
            enemy.takeDamage(damage);
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    // TODO: Remove
    /**
     * Not used anymore
     *
     * @deprecated use isColliding(StaticEntity entity) instead
     */
    public void checkIfTouchingPlayer(Player player) {
        /*if (hitBoxIntersectWith(player)) {
            takeDamage(player.dealDamage());
            player.takeDamage(dealDamage());
        }*/
    }

    public boolean isColliding(StaticEntity entity) {
        return hitBoxIntersectWith(entity) && !isDead();
    }

    public int getId() {
        return id;
    }
}
