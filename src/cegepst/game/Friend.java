package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.entities.MovableEntity;

import java.awt.*;
import java.util.Random;

public class Friend extends MovableEntity {

    private Color color;
    private Random random;

    public Friend(int x, int y) {
        setDimension(10, 10);
        teleport(x, y);
        random = new Random();
        color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        EntityRepository.getInstance().registerEntity(this);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(58, 201, 156));
    }
}
