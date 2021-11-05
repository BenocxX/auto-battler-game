package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Trigger extends StaticEntity {

    public boolean isTriggered(StaticEntity entity) {
        return intersectWith(entity);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
    }
}
