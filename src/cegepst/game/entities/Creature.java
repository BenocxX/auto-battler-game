package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.RandomHandler;

import java.awt.*;

public class Creature extends Item {

    private Color color;

    public Creature(int id, int x, int y) {
        super(id);
        setDimension(10, 10);
        teleport(x, y);
        color = new Color(RandomHandler.getInt(100, 255), RandomHandler.getInt(100, 255), RandomHandler.getInt(100, 255));
    }

    @Override
    public void draw(Buffer buffer) {
        if (!isBought) {
            buffer.drawRectangle(x, y, width, height, color);
        }
    }
}
