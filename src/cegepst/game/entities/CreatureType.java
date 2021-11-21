package cegepst.game.entities;

import java.awt.*;

public enum CreatureType {

    ZOMBIE("Zombie", new Color(101, 128, 68)),
    FISH("Fish", new Color(74, 184, 219)),
    DOG("Dog", new Color(255, 0, 0)),
    VAMPIRE("Vampire", new Color(180, 170, 144)),
    BAT("Bat", new Color(59, 59, 59));

    private String name;
    private Color color;

    CreatureType(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
