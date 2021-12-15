package cegepst.game.entities.plants;

import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.entities.projectiles.Pea;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.projectiles.Sun;
import cegepst.game.resources.Sprite;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum PlantType {

    PEASHOOTER("Peashooter", 100, 60, 60, 60,
            125, Sprite.PEASHOOTER.getImage(), Pea.class),
    GATLINGPEA("Gatlingpea", 115, 30, 60, 60,
            150, Sprite.GATLING_PEA.getImage(), Pea.class),
    SUNFLOWER("Sunflower", 75, 240, 60, 80,
            100, Sprite.SUNFLOWER.getImage(), Sun.class);

    private String name;
    private int health;
    private int abilityCooldown;
    private int width;
    private int height;
    private int sunPrice;
    private Image image;
    private Constructor<? extends Projectile> projectileConstructor;

    PlantType(String name, int health,
              int abilityCooldown, int width,
              int height, int sunPrice,
              Image image, Class<? extends Projectile> projectileClass) {
        this.name = name;
        this.health = health;
        this.abilityCooldown = abilityCooldown;
        this.width = width;
        this.height = height;
        this.sunPrice = sunPrice;
        this.image = image;
        try {
            projectileConstructor = projectileClass.getConstructor(Plant.class, int.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSunPrice() {
        return sunPrice;
    }

    public Projectile generateNewProjectile(Plant plant, int x, int y) {
        try {
            return projectileConstructor.newInstance(plant, x, y);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Image getResizedImage() {
        return SpriteHandler.resizeImage(image, Image.SCALE_SMOOTH, width, height);
    }
}
