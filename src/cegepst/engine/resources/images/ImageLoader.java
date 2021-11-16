package cegepst.engine.resources.images;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader {

    private static final ClassLoader classLoader = ImageLoader.class.getClassLoader();

    public static Image loadImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage loadBufferedImage(String path) {
        return (BufferedImage) loadImage(path);
    }
}
