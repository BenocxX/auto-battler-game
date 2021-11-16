package cegepst.game;

import cegepst.engine.resources.images.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Sprite {

    MAP_1("./images/demo.png"),
    PLAYER_SPRITE_SHEET("./images/player.png");

    private final String path;
    private Image image;
    private BufferedImage bufferedImage;

    Sprite(String path) {
        this.path = path;
    }

    public Image getImage() {
        if (image == null) {
            image = ImageLoader.loadImage(path);
        }
        return image;
    }

    public BufferedImage getBufferedImage() {
        if (bufferedImage == null) {
            bufferedImage = ImageLoader.loadBufferedImage(path);
        }
        return bufferedImage;
    }
}
