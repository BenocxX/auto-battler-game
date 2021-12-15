package cegepst.game.map;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;

public class Cell extends StaticEntity {

    public final static int WIDTH = 55;
    public final static int HEIGHT = 90;

    private TriggerArea area;
    private boolean isClicked;
    private boolean isEmpty;

    public Cell(int x, int y) {
        setDimension(WIDTH, HEIGHT);
        teleport(x, y);
        area = new TriggerArea(x, y, width, height);
        isEmpty = true;
    }

    @Override
    public void draw(Buffer buffer) {
        area.draw(buffer);
        //buffer.drawOutlineRectangle(getX(), getY(), WIDTH, HEIGHT, Color.WHITE);
    }

    public boolean isClicked(Point mousePosition) {
        isClicked = getBounds().contains(mousePosition);
        if (isClicked) {
            EventSystem.getInstance().onCellClick(this);
        }
        return isClicked;
    }

    public void placeEntity(StaticEntity entity) {
        isEmpty = false;
        Rectangle areaRectangle = new Rectangle(getX(), getY(), area.getWidth(), area.getHeight());
        entity.teleport(CenteringMachine.centerHorizontally(areaRectangle, entity.getWidth()),
                CenteringMachine.centerVertically(areaRectangle, entity.getHeight()));
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
