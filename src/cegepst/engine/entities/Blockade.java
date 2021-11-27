package cegepst.engine.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.game.settings.GameSettings;

import java.awt.*;

public class Blockade extends StaticEntity {

    public Blockade() {
        CollidableRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.DEBUG_MODE) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }
}
