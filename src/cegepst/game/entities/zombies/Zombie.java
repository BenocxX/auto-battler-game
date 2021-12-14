package cegepst.game.entities.zombies;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Zombie extends MovableEntity {

    private final static int COOLDOWN_RESET = 2;

    private Zombies type;
    private int health;
    private int damage;
    private int speed;
    private Image image;
    private int cooldown;

    public Zombie(Zombies type) {
        this.type = type;
        setDimension(type.getWidth(), type.getHeight());
        image = type.getResizedImage(width, height);
        health = type.getHealth();
        damage = type.getDamage();
        speed = type.getSpeed();
        setSpeed(speed);
        cooldown = 30;
    }

    public void update() {
        cooldown--;
        if (cooldown <= 0) {
            move(Direction.LEFT);
            cooldown = COOLDOWN_RESET;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x,  y);
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int dealDamage() {
        return damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isColliding(StaticEntity entity) {
        return hitBoxIntersectWith(entity) && !isDead();
    }
}
