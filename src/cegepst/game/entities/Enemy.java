package cegepst.game.entities;

import cegepst.engine.CollidableRepository;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.resources.Sprite;

public abstract class Enemy extends MovableEntity {

    protected Animator animator;
    protected int health;
    protected int damage;

    public Enemy() {
        setDimension(32, 32);
        teleport(700, 450);
        setSpeed(1);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
    }

    public void checkIfTouchingPlayer(Player player) {
        if (hitBoxIntersectWith(player)) {
            takeDamage(player.dealDamage());
            player.takeDamage(dealDamage());
            System.out.println();
            System.out.println();
        }
    }

    protected void takeDamage(int damageTaken) {
        health -= damageTaken;
    }

    protected int dealDamage() {
        return damage;
    }
}
