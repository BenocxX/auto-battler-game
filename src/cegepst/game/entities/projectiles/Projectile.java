package cegepst.game.entities.projectiles;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.game.entities.plants.Plant;

public abstract class Projectile extends MovableEntity {

    protected final Direction direction;
    protected int cooldown;

    public Projectile(Plant plant, int x, int y) {
        direction = Direction.RIGHT;
        cooldown = 120;
        teleport(plant.getX() + plant.getWidth() - 15,
                plant.getY() + 5 - 2);
        teleport(x ,y);
    }

    public abstract void draw(Buffer buffer);
    public abstract int dealDamage();

    @Override
    public void update() {
        cooldown--;
        if (cooldown <= 0) {
            cooldown = 0;
        }
    }
}
