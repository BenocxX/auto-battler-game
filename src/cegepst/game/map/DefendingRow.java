package cegepst.game.map;

import cegepst.engine.Buffer;
import cegepst.game.entities.Player;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;

public class DefendingRow extends Row {

    public final static int WIDTH = 50;
    public final static int HEIGHT = 50;

    private AttackingRow row;

    public DefendingRow(AttackingRow row) {
        super(row.getX() - WIDTH, row.getY(), WIDTH, HEIGHT);
        this.row = row;
    }

    @Override
    public void draw(Buffer buffer) {
        area.draw(buffer);
        buffer.drawOutlineRectangle(getX(), getY(), WIDTH, HEIGHT, Color.WHITE);
    }

    public AttackingRow getRow() {
        return row;
    }

    public void movePlayer(Player player) {
        Rectangle areaRectangle = new Rectangle(getX(), getY(), area.getWidth(), area.getHeight());
        player.teleport(CenteringMachine.centerHorizontally(areaRectangle, player.getHitBox()) - 20,
                CenteringMachine.centerVertically(areaRectangle, player.getHitBox()) - 10);
    }
}
