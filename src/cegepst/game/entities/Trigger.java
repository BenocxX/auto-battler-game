package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Trigger extends StaticEntity {

    public Trigger() {
        EntityRepository.getInstance().registerEntity(this);
    }

    public boolean isTriggered(StaticEntity entity) {
        return intersectWith(entity);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
    }
}