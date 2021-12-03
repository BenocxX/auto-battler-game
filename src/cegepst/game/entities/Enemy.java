package cegepst.game.entities;

import cegepst.engine.CollidableRepository;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.resources.images.Animator;
import cegepst.game.resources.Sprite;

public abstract class Enemy extends MovableEntity {

    protected Animator animator;
    protected int health;
    protected int damage;

    public Enemy() {
        setDimension(32, 32);
        teleport(500, 450);
        setSpeed(1);
        animator = new Animator(Sprite.PLAYER_SPRITE_SHEET.getBufferedImage(), 8);
        CollidableRepository.getInstance().registerEntity(this);
    }

    protected void takeDamage(int damageTaken) {
        System.out.println("Ouch!");
        health -= damageTaken;
    }

    protected int dealDamage() {
        System.out.println("BArrghh!");
        return damage;
    }
}
