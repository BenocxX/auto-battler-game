package cegepst.game.entities.projectiles;

import cegepst.engine.Buffer;
import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.PlantOld;
import cegepst.game.eventsystem.EventSystem;

public class Sun extends Projectile {

    private final static Projectiles type = Projectiles.SUN;

    public Sun(Plant plant, int x, int y) {
        super(plant, x, y);
        setSpeed(type.getSpeed());
        setDimension(type.getWidth(), type.getHeight());
    }

    @Override
    public void update() {
        super.update();
        if (cooldown == 0) {
            EventSystem.getInstance().onSunCreation(this);
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawCircle(x, y, 10, type.getColor());
    }

    @Override
    public int dealDamage() {
        return type.getDamage();
    }
}
