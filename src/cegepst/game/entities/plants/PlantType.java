package cegepst.game.entities.plants;

import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.entities.projectiles.EmptyProjectile;
import cegepst.game.entities.projectiles.Pea;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.projectiles.Sun;
import cegepst.game.resources.Sprite;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum PlantType {

    PEASHOOTER(1, "Peashooter", 100, 60, 60, 60,
            125, Sprite.PEASHOOTER.getImage(), Pea.class),
    GATLINGPEA(2, "Gatlingpea", 115, 30, 60, 60,
            150, Sprite.GATLING_PEA.getImage(), Pea.class),
    SUNFLOWER(3, "Sunflower", 75, 240, 60, 80,
            100, Sprite.SUNFLOWER.getImage(), Sun.class),
    WALL_NUT(4, "Wall Nut", 300, 1000, 60, 70,
            150, Sprite.WALL_NUT.getImage(), EmptyProjectile.class);

    private int id;
    private String name;
    private int health;
    private int abilityCooldown;
    private int width;
    private int height;
    private int sunPrice;
    private Image image;
    private Constructor<? extends Projectile> projectileConstructor;

    PlantType(int id, String name, int health,
              int abilityCooldown, int width,
              int height, int sunPrice,
              Image image, Class<? extends Projectile> projectileClass) {
        this.id = id;
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
        /* Note
         *
         * I needed a way to instantiate lots of new projectile children.
         * But I couldn't know which children unless I used 'new Pea()' in
         * the enum parameter list. Using new Projectile() as a parameter
         * would have caused a bunch of problems, the first one being that
         * it would only create one object of type projectile and update that
         * one only. The second one being that I can't pass parameter like
         * the plant shooting the projectile in enum parameter list.
         *
         * I found a way to get the constructor of the children classes of
         * Projectile. Using this, I can instantiate projectiles by only passing
         * the class through enum parameter list. I saw on internet that this
         * is called Reflection. I don't totally understand what reflection is
         * but I get why it works and how to use it for what I need. :)
         */
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

    public int getId() {
        return id;
    }
}
