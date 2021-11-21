package cegepst.game.eventsystem;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Box extends StaticEntity implements TriggerAreaListener {

    private static final Color TRIGGERED_COLOR = new Color(84, 255, 0);
    private static final Color UNTRIGGERED_COLOR = new Color(255, 139, 0);;

    private int id;
    private Color color = UNTRIGGERED_COLOR;

    public Box(int id) {
        this.id = id;
        setDimension(50, 50);
        EventSystem.getInstance().addTriggerAreaListener(this);
    }

    @Override
    public void onTriggerEnter(int triggerId) {
        if (triggerId == id) {
            color = TRIGGERED_COLOR;
        }
    }

    @Override
    public void onTrigger(int triggerId) {
        if (triggerId == id) {
        }
    }

    @Override
    public void onTriggerLeave(int triggerId) {
        if (triggerId == id) {
            color = UNTRIGGERED_COLOR;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, color);
        buffer.drawCenteredText("ID: #" + id, getBounds());
    }
}
