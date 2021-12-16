package cegepst.game.entities.projectiles;

import java.awt.*;

public enum Projectiles {

    PEA(8, 4, 5, 10, new Color(113, 180, 55)),
    SUN(10, 5, 0, 0, new Color(227, 168, 52)),
    EMPTY_PROJECTILE(0, 0, 0, 0, new Color(0, 0, 0, 0));

    private int width;
    private int height;
    private int speed;
    private int damage;
    private Color color;

    Projectiles(int width, int height, int speed, int damage, Color color) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.damage = damage;
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public Color getColor() {
        return color;
    }
}
