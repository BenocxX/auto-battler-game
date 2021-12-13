package cegepst.game.entities.plants;

import cegepst.engine.Buffer;

public class Peashooter extends Plant {

    public final static int WIDTH = 60;
    public final static int HEIGHT = 60;
    public final static int COOLDOWN_RESET = 50;

    private int cooldown;

    public Peashooter(int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        cooldown = 0;
        name = "Peashooter";
        image = Plants.PEASHOOTER.getImage(WIDTH, HEIGHT);
    }

    @Override
    public void update() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }

    public Projectile ability() {
        cooldown = COOLDOWN_RESET;
        return new Projectile(this);
    }

    public boolean isCooldownOver() {
        return cooldown == 0;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
    }

    @Override
    public Plant getPlantOfSameType() {
        return new Peashooter(0, 0);
    }
}
