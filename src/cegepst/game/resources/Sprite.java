package cegepst.game.resources;

import cegepst.engine.resources.images.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Sprite {

    MAP_1("./images/demo.png"),
    PLAYER_SPRITE_SHEET("./images/player.png"),
    SHOP_SPRITE_SHEET("./images/shop.png"),
    SAP_TILE_SPRITE("./images/sap/tile.png"),
    SAP_ANT_SPRITE("./images/sap/ant.png"),
    SAP_BAT_SPRITE("./images/sap/bat.png"),
    SAP_DOG_SPRITE("./images/sap/dog.png"),
    SAP_FISH_SPRITE("./images/sap/fish.png"),
    SAP_DRAGON_SPRITE("./images/sap/dragon.png");

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
