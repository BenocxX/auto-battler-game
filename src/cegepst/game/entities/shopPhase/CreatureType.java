package cegepst.game.entities.shopPhase;

import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.resources.Sprite;

import java.awt.*;

public enum CreatureType {

    ANT("Ant", "A small bug pretty hard to kill.",
            Sprite.SAP_ANT_SPRITE.getImage()),
    FISH("Fish", "Does big splash splash.",
            Sprite.SAP_FISH_SPRITE.getImage()),
    DOG("Dog", "His bark his louder than you think.",
            Sprite.SAP_DOG_SPRITE.getImage()),
    DRAGON("Dragon", "Mystic creature with a really hot breath.",
            Sprite.SAP_DRAGON_SPRITE.getImage()),
    BAT("Bat", "Flies faster than a small airplane.",
            Sprite.SAP_BAT_SPRITE.getImage());

    private String name;
    private String description;
    private Image sprite;

    CreatureType(String name, String description, Image sprite) {
        this.name = name;
        this.description = description;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Image getSprite(int width, int height) {
        return SpriteHandler.resizeImage(sprite, Image.SCALE_SMOOTH, width, height);
    }
}
