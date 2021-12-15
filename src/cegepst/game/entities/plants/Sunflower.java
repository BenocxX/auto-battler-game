package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.projectiles.Sun;
import cegepst.game.resources.Sprite;

import java.awt.*;

public class Sunflower extends Plant {

    public final static int WIDTH = 60;
    public final static int HEIGHT = 80;
    public final static int COOLDOWN_RESET = 240;

    private int cooldown;

    public Sunflower(int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        cooldown = COOLDOWN_RESET;
        name = "Sunflower";
        image = SpriteHandler.resizeImage(Sprite.SUNFLOWER.getImage(),
                Image.SCALE_SMOOTH, width, height);
        health = 75;
        sunPrice = 2;
    }

    @Override
    public void update() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
        buffer.drawHorizontallyCenteredText("HP: " + health, getBounds(), y - 10);
    }

    @Override
    public Projectile ability() {
        cooldown = COOLDOWN_RESET;
        return new Sun(this, x + width - 15, y + 5 - 2);
    }

    @Override
    public boolean isCooldownOver() {
        return cooldown == 0;
    }

    @Override
    public Plant getPlantOfSameType() {
        return new Sunflower(0, 0);
    }
}
