package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.eventsystem.EventSystem;

import java.awt.*;

public abstract class Plant extends StaticEntity {

    protected Image image;
    protected String name;
    protected int health;
    protected int sunPrice;

    // Refactor: Use Plants type to make new Plant()

    public abstract void update();
    @Override
    public abstract void draw(Buffer buffer);
    public abstract Projectile ability();
    public abstract boolean isCooldownOver();
    public abstract Plant getPlantOfSameType();

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            EventSystem.getInstance().onPlantDeath(this);
        }
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getSunPrice() {
        return sunPrice;
    }
}
