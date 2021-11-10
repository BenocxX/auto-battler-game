package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.game.triggers.Triggerable;

import java.awt.*;

public class BuyStation extends MovableEntity implements Triggerable {

    private Friend friendForSell;
    private boolean isSelected;
    private boolean isBought;

    public BuyStation(int x, int y) {
        setDimension(30, 30);
        teleport(x, y);
        friendForSell = new Friend(x + 10, y + 10);
        CollidableRepository.getInstance().registerEntity(this);
    }

    public void buy() {
        isBought = true;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isSelected) {
            buffer.drawRectangle(x, y, width, height, new Color(137, 106, 77));
            buffer.drawText("Buy", x + 5, y - 5, new Color(255, 255, 255));
            buffer.drawText("(Use F to Buy)", RenderingEngine.WIDTH - 90, 40, new Color(255, 255, 255));
        } else {
            buffer.drawRectangle(x, y, width, height, new Color(94, 71, 47));
        }
        if (!isBought) {
            friendForSell.draw(buffer);
        }
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
