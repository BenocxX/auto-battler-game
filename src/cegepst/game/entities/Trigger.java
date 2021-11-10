package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.GameSettings;

import java.awt.*;

public class Trigger extends StaticEntity {

    public boolean isTriggered(StaticEntity triggerer) {
        return intersectWith(triggerer);
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.DEBUG_MODE) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }
}
