package cegepst.game.entities.plants;

import cegepst.engine.Buffer;

public class Sunflower extends Plant {

    public final static int WIDTH = 60;
    public final static int HEIGHT = 80;
    public final static int COOLDOWN_RESET = 10;

    private int cooldown;

    public Sunflower(int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        cooldown = 0;
        name = "Sunflower";
        image = Plants.SUNFLOWER.getImage(WIDTH, HEIGHT);
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
    }

    @Override
    public Projectile ability() {
        cooldown = COOLDOWN_RESET;
        return new Projectile(this);
    }

    @Override
    public boolean isCooldownOver() {
        return cooldown == 0;
    }
}
