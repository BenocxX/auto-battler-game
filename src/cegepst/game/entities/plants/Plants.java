package cegepst.game.entities.plants;

import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.resources.Sprite;

import java.awt.*;

public enum Plants {

    PEASHOOTER(Sprite.PEASHOOTER.getImage()),
    SUNFLOWER(Sprite.SUNFLOWER.getImage());

    private Image image;

    Plants(Image image) {
        this.image = image;
    }

    public Image getImage(int width, int height) {
        return SpriteHandler.resizeImage(image, Image.SCALE_SMOOTH, width, height);
    }
}
