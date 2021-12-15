package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.eventsystem.EventSystem;

import java.awt.*;

public class Plant extends StaticEntity {

    private PlantType plantType;
    private Image image;
    private String name;
    private int health;
    private int sunPrice;
    private int cooldown;

    public Plant(PlantType plantType) {
        this.plantType = plantType;
        setDimension(plantType.getWidth(), plantType.getHeight());
        image = plantType.getResizedImage();
        name = plantType.getName();
        health = plantType.getHealth();
        sunPrice = plantType.getSunPrice();
        cooldown = 0;
    }

    public void update() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
        buffer.drawHorizontallyCenteredText("HP: " + health, getBounds(), y - 10);
    }

    public Projectile ability() {
        cooldown = plantType.getAbilityCooldown();
        return plantType.generateNewProjectile(this, x + width - 15, y + 5 - 2);
    }

    public boolean isCooldownOver() {
        return cooldown == 0;
    }

    public Plant getPlantOfSameType() {
        return new Plant(plantType);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            // TODO: Change parameter
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
