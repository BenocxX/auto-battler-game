package cegepst.game.entities.miscellaneous;

import cegepst.engine.Buffer;
import cegepst.engine.controls.Direction;
import cegepst.engine.entities.MovableEntity;
import cegepst.game.entities.plants.Plant;

import java.awt.*;

public class Projectile extends MovableEntity {

    private final Direction direction;

    public Projectile(Plant plant) {
        direction = Direction.RIGHT;
        setSpeed(5);
        teleport(plant.getX() + plant.getWidth() - 15,
                plant.getY() + 5 - 2);
        setDimension(8, 4);
    }

    @Override
    public void update() {
        move(direction);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawCircle(x, y, 10, new Color(113, 180, 55));
    }
}
