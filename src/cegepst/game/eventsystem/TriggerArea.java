package cegepst.game.eventsystem;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.GameSettings;

import java.awt.*;

public class TriggerArea extends StaticEntity {

    private int id;
    private boolean isTriggered;

    public TriggerArea(int id) {
        this.id = id;
        isTriggered = false;
    }

    public void triggerCheck(StaticEntity triggerer) {
        if (intersectWith(triggerer)) {
            EventSystem.getInstance().onTriggerAreaTriggered(id);
            if (!isTriggered) {
                EventSystem.getInstance().onTriggerAreaEnter(id);
                isTriggered = true;
            }
        } else {
            if (isTriggered) {
                EventSystem.getInstance().onTriggerAreaLeave(id);
                isTriggered = false;
            }
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.DEBUG_MODE) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }
}
