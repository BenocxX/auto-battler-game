package cegepst.game.entities.projectiles;

import cegepst.engine.Buffer;
import cegepst.game.entities.plants.Plant;

public class EmptyProjectile extends Projectile {

    private final static Projectiles type = Projectiles.EMPTY_PROJECTILE;

    public EmptyProjectile(Plant plant, int x, int y) {
        super(plant, x, y);
        setSpeed(type.getSpeed());
        setDimension(type.getWidth(), type.getHeight());
    }

    @Override
    public void draw(Buffer buffer) {
        // Invisible
    }

    @Override
    public int dealDamage() {
        return 0;
    }
}
