package cegepst.game.entities;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.GameSettings;
import cegepst.game.eventsystem.EventSystem;

import java.awt.*;

public class TriggerArea extends StaticEntity {

    private int id;
    private boolean isTriggered;

    public TriggerArea(int id, int x, int y, int width, int height) {
        this.id = id;
        isTriggered = false;
        teleport(x, y);
        setDimension(width, height);
    }

    public void triggerCheck(StaticEntity triggerer) {
        if (intersectWith(triggerer)) {
            triggerEnterEventCheck();
            triggeringEventCheck();
        } else {
            triggerLeaveEventCheck();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.DEBUG_MODE) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }

    private void triggerEnterEventCheck() {
        if (!isTriggered) {
            EventSystem.getInstance().onTriggerAreaEnter(id);
            isTriggered = true;
        }
    }

    private void triggeringEventCheck() {
        if (isTriggered) {
            EventSystem.getInstance().onTriggerAreaTriggered(id);
        }
    }

    private void triggerLeaveEventCheck() {
        if (isTriggered) {
            EventSystem.getInstance().onTriggerAreaLeave(id);
            isTriggered = false;
        }
    }
}
