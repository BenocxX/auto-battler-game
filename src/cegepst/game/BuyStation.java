package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class BuyStation extends MovableEntity implements Triggerable {

    private Friend friendForSell;
    private boolean isSelected;

    public BuyStation(int x, int y) {
        setDimension(30, 30);
        teleport(x, y);
        friendForSell = new Friend(x + 10, y + 10);
        EntityRepository.getInstance().registerEntity(this);
        CollidableRepository.getInstance().registerEntity(this);
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

    @Override
    public void trigger() {
        isSelected = true;
    }

    @Override
    public void untrigger() {
        isSelected = false;
    }
}
