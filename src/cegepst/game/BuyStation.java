package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class BuyStation extends MovableEntity {

    private ZoneTrigger zoneTrigger;
    private Friend friendForSell;
    private boolean isSelected;

    public BuyStation(int x, int y) {
        setDimension(30, 30);
        teleport(x, y);
        zoneTrigger = new ZoneTrigger(x - 10, y + height + 10, 50, 50);
        friendForSell = new Friend(x + 10, y + 10);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean checkIfSelected(StaticEntity entity) {
        return zoneTrigger.intersectWith(entity);
    }

    @Override
    public void draw(Buffer buffer) {
        if (isSelected) {
            buffer.drawRectangle(x, y, width, height, new Color(137, 106, 77));
        } else {
            buffer.drawRectangle(x, y, width, height, new Color(94, 71, 47));
        }
        friendForSell.draw(buffer);
    }
}
