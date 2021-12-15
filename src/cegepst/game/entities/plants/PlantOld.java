package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.projectiles.Pea;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.eventsystem.EventSystem;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public abstract class PlantOld extends StaticEntity {

    protected Image image;
    protected String name;
    protected int health;
    protected int sunPrice;

    public abstract void update();
    @Override
    public abstract void draw(Buffer buffer);
    public abstract Projectile ability();
    public abstract boolean isCooldownOver();
    public abstract PlantOld getPlantOfSameType();

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            //EventSystem.getInstance().onPlantDeath(this);
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
