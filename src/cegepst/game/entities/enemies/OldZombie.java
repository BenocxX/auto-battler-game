package cegepst.game.entities.enemies;

import cegepst.engine.Buffer;

public class OldZombie extends Enemy {

    public OldZombie() {
        super();
        animator.loadAnimations(96, 128, width, height, 3);
        health = Enemies.ZOMBIE.getHealth();
        damage = Enemies.ZOMBIE.getDamage();
        speed = Enemies.ZOMBIE.getSpeed();
        setSpeed(speed);
    }

    @Override
    public void update() {
        super.update();
        animator.updateAnimationFrame(hasMoved());
        moveLeft();
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(animator.getImage(getDirection()), x, y);
        buffer.drawHorizontallyCenteredText("HP: " + health, getBounds(), y - 10);
    }
}
