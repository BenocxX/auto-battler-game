package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Trigger extends StaticEntity {

    // Use HashMap<Trigger, Triggerable>()

    // Make the zone trigger have a Static Entity to activate when colliding

    // Make an arrayList of zone triggers in AutoBattlerGame class and
    // loop through them to check if triggered or not

    public Trigger(int x, int y, int width, int height) {
        setDimension(width, height);
        teleport(x, y);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
    }
}
