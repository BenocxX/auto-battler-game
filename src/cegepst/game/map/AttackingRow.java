package cegepst.game.map;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.game.entities.enemies.Enemy;

import java.awt.*;
import java.util.ArrayList;

public class AttackingRow extends Row {

    public final static int WIDTH = 700;
    public final static int HEIGHT = 50;

    public AttackingRow(int y) {
        super(RenderingEngine.WIDTH - WIDTH, y, WIDTH, HEIGHT);
    }

    @Override
    public void draw(Buffer buffer) {
        area.draw(buffer);
        buffer.drawOutlineRectangle(getX(), getY(), WIDTH, HEIGHT, Color.BLACK);
    }

    public ArrayList<Enemy> getEnemies(ArrayList<Enemy> enemies) {
        ArrayList<Enemy> enemiesInRow = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (area.intersectWith(enemy)) {
                enemiesInRow.add(enemy);
            }
        }
        return enemiesInRow;
    }
}
