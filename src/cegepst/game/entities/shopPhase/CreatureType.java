package cegepst.game.entities.shopPhase;

import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.resources.Sprite;

import java.awt.*;

public enum CreatureType {

    ANT("Ant", Sprite.SAP_ANT_SPRITE.getImage()),
    FISH("Fish", Sprite.SAP_FISH_SPRITE.getImage()),
    DOG("Dog", Sprite.SAP_DOG_SPRITE.getImage()),
    DRAGON("Dragon", Sprite.SAP_DRAGON_SPRITE.getImage()),
    BAT("Bat", Sprite.SAP_BAT_SPRITE.getImage());

    private String name;
    private Image sprite;

    CreatureType(String name, Image sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public Image getSprite(int width, int height) {
        return SpriteHandler.resizeImage(sprite, Image.SCALE_SMOOTH, width, height);
    }
}
