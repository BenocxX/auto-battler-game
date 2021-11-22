package cegepst.engine.resources.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteHandler {

    public static Image[] getFrames(
            BufferedImage spriteSheet,
            int initialX,
            int initialY,
            int width,
            int height,
            int nbImages) {
        Image[] frames = new Image[nbImages];
        for (int i = 0; i < nbImages; i++) {
            frames[i] = spriteSheet.getSubimage(initialX, initialY, width, height);
            initialX += width;
        }
        return frames;
    }

    public static Image getFrame(BufferedImage spriteSheet,
                                 int initialX,
                                 int initialY,
                                 int width,
                                 int height) {
        return spriteSheet.getSubimage(initialX, initialY, width, height);
    }

    public static Image resizeImage(Image image, int quality, int width, int height) {
        return image.getScaledInstance(width, height, quality);
    }
}
