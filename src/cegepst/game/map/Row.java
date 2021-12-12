package cegepst.game.map;

import cegepst.engine.Buffer;
import cegepst.game.entities.miscellaneous.TriggerArea;

public abstract class Row {

    protected TriggerArea area;

    public Row(int x, int y, int width, int height) {
        area = new TriggerArea(width, height);
        area.teleport(x, y);
    }

    public abstract void draw(Buffer buffer);

    public int getX() {
        return area.getX();
    }

    public int getY() {
        return area.getY();
    }
}
