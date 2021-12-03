package cegepst.game.map;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.Blockade;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.resources.Sprite;

import java.awt.*;
import java.util.ArrayList;

public class World {

    private Image mapSprite;
    private ArrayList<Blockade> worldBorders;

    public World(Image mapImage) {
        initializeWorldBorders();
        mapSprite = SpriteHandler.resizeImage(mapImage,
                Image.SCALE_SMOOTH, RenderingEngine.WIDTH, RenderingEngine.HEIGHT);
    }

    public void draw(Buffer buffer) {
        buffer.drawImage(mapSprite, 0, 0);
        for (Blockade worldBorder : worldBorders) {
            worldBorder.draw(buffer);
        }
    }

    private void initializeWorldBorders() {
        worldBorders = new ArrayList<>();
        Blockade topBorder = new Blockade();
        topBorder.teleport(0, 350);
        topBorder.setDimension(RenderingEngine.WIDTH, 1);

        Blockade bottomBorder = new Blockade();
        bottomBorder.teleport(0, RenderingEngine.HEIGHT);
        bottomBorder.setDimension(RenderingEngine.WIDTH, 1);

        Blockade leftBorder = new Blockade();
        leftBorder.teleport(0, 0);
        leftBorder.setDimension(1, RenderingEngine.HEIGHT);

        Blockade rightBorder = new Blockade();
        rightBorder.teleport(RenderingEngine.WIDTH, 0);
        rightBorder.setDimension(1, RenderingEngine.HEIGHT);

        worldBorders.add(topBorder);
        worldBorders.add(bottomBorder);
        worldBorders.add(leftBorder);
        worldBorders.add(rightBorder);
    }
}
