package cegepst.game.entities;

import cegepst.engine.Buffer;

public class Zombie extends Enemy {

    public Zombie() {
        super();
        animator.loadAnimations(96, 128, width, height, 3);
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
    }
}