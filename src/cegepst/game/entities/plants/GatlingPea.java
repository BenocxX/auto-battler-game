package cegepst.game.entities.plants;

import cegepst.engine.Buffer;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.entities.projectiles.Pea;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.resources.Sprite;

import java.awt.*;

public class GatlingPea extends Plant {

    public final static int WIDTH = 60;
    public final static int HEIGHT = 60;
    public final static int COOLDOWN_RESET = 30;

    private int cooldown;

    public GatlingPea(int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        cooldown = 0;
        name = "Gatling Pea";
        image = SpriteHandler.resizeImage(Sprite.GATLING_PEA.getImage(),
                Image.SCALE_SMOOTH, width, height);
        health = 115;
        sunPrice = 10;
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
        return new Pea(this, x + width - 15, y + 8);
    }

    @Override
    public boolean isCooldownOver() {
        return cooldown == 0;
    }

    @Override
    public Plant getPlantOfSameType() {
        return new GatlingPea(0, 0);
    }
}
