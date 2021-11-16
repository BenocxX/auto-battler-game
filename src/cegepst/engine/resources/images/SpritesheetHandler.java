package cegepst.engine.resources.images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpritesheetHandler {

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
}
