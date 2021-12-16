package cegepst.game.entities.zombies;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.resources.sounds.SoundPlayer;
import cegepst.game.entities.plants.Plant;

import java.awt.*;

public class Zombie extends MovableEntity {

    private final static int MOVING_COOLDOWN_RESET = 2;
    private final static int EATING_COOLDOWN_RESET = 60;

    private Zombies type;
    private int health;
    private int damage;
    private int speed;
    private Image image;
    private int movingCooldown;
    private int eatingCooldown;
    private boolean isEating;
    private Plant eatingPlant;

    public Zombie(Zombies type) {
        this.type = type;
        setDimension(type.getWidth(), type.getHeight());
        image = type.getResizedImage(width, height);
        health = type.getHealth();
        damage = type.getDamage();
        speed = type.getSpeed();
        setSpeed(speed);
        movingCooldown = 30;
        eatingCooldown = EATING_COOLDOWN_RESET;
    }

    public void update() {
        if (RandomHandler.getInt(5000) == 0) {
            SoundPlayer.play("./sounds/best1.wav");
        }
        if (isEating && !eatingPlant.isDead()) {
            eatingCooldown--;
            if (eatingCooldown <= 0) {
                eatingPlant.takeDamage(damage);
                eatingCooldown = EATING_COOLDOWN_RESET;
            }
        } else {
            movingCooldown--;
            if (movingCooldown <= 0) {
                move(Direction.LEFT);
                movingCooldown = MOVING_COOLDOWN_RESET;
            }
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x,  y);
    }

    public void takeDamage(int damage) {
        if (RandomHandler.getInt(100) == 0) {
            SoundPlayer.play("./sounds/best1.wav");
        }
        health -= damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public boolean isColliding(StaticEntity entity) {
        return hitBoxIntersectWith(entity) && !isDead();
    }

    public void setEating(boolean isEating, Plant eatingPlant) {
        this.isEating = isEating;
        this.eatingPlant = eatingPlant;
    }
}
