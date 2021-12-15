package cegepst.game.resources;

import cegepst.engine.resources.images.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public enum Sprite {

    SHOP_MAP("./images/sap/map.png"),
    PLAYER_SPRITE_SHEET("./images/player.png"),
    SAP_TILE_SPRITE("./images/sap/tile.png"),

    PVZ_MAP("./images/PVZ/map.jpg"),
    PEASHOOTER("./images/PVZ/plants/peashooter.png"),
    SUNFLOWER("./images/PVZ/plants/Sunflower.png"),
    GATLING_PEA("./images/PVZ/plants/gatlingpea.png"),
    FLAG_ZOMBIE("./images/PVZ/zombies/FlagZombie.png"),
    CONE_HEAD_ZOMBIE("./images/PVZ/zombies/ConeheadZombie.png"),
    BUCKET_HEAD_ZOMBIE("./images/PVZ/zombies/BucketheadZombie.png"),
    MENU("./images/PVZ/menu.jpg");

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
