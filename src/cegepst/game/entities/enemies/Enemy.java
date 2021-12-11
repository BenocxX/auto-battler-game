package cegepst.game.entities.enemies;

import cegepst.engine.entities.MovableEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.entities.Killable;
import cegepst.game.entities.Player;
import cegepst.game.resources.Sprite;

public abstract class Enemy extends MovableEntity implements Killable {

    protected Animator animator;
    protected int health;
    protected int damage;

    public Enemy() {
        setDimension(32, 32);
        teleport(700, 450);
        setSpeed(1);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }

    @Override
    public int dealDamage() {
        return damage;
    }

    public void checkIfTouchingPlayer(Player player) {
        if (hitBoxIntersectWith(player)) {
            takeDamage(player.dealDamage());
            player.takeDamage(dealDamage());
        }
    }
}
