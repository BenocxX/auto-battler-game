package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;

import java.awt.*;

public class Creature extends MovableEntity {

    private Color color;
    private boolean isBought;

    public Creature(int x, int y) {
        setDimension(10, 10);
        teleport(x, y);
        color = new Color(RandomHandler.getInt(100, 256), RandomHandler.getInt(100, 256), RandomHandler.getInt(100, 256));
        isBought = false;
    }

    @Override
    public void draw(Buffer buffer) {
        if (!isBought) {
            buffer.drawRectangle(x, y, width, height, color);
        }
    }

    public void buy() {
        isBought = true;
    }
}
