package cegepst.game.entities.zombies;

import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.resources.Sprite;

import java.awt.*;

public enum Zombies {

    FLAG_ZOMBIE(100, 30, 2, 180, 265, Sprite.FLAG_ZOMBIE.getImage()),
    CONE_HEAD_ZOMBIE(150, 25, 3, 180, 347, Sprite.FLAG_ZOMBIE.getImage()),
    BUCKET_HEAD_ZOMBIE(200, 20, 3, 180, 312, Sprite.FLAG_ZOMBIE.getImage());

    private int health;
    private int damage;
    private int speed;
    private int width;
    private int height;
    private Image image;

    Zombies(int health, int damage, int speed, int width, int height, Image image) {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getResizedImage(int width, int height) {
        return SpriteHandler.resizeImage(image,
                Image.SCALE_SMOOTH, width, height);
    }
}
