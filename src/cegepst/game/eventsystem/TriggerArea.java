package cegepst.game.eventsystem;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.GameSettings;

import java.awt.*;

public class TriggerArea extends StaticEntity {

    private int id;
    private boolean isTriggered;

    /**
     * Le constructor du TriggerArea
     */
    public TriggerArea(int id) {
        this.id = id;
        isTriggered = false;
    }

    /**
     * Vérifie si le triggerArea est déclenché. Selon la réponse,
     * différents event sont call par le EventSystem. Lorsqu'un event
     * est déclenché, on passe en paramètre le id du trigger ayant
     * déclenché l'event afin de permettre à l'objet se faisant
     * déclencher de vérifier qu'il est bien relié au bon déclencheur.
     */
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

    /**
     * Draw le triggerArea à l'écran si le DEBUG_MODE est activé
     */
    @Override
    public void draw(Buffer buffer) {
        if (GameSettings.DEBUG_MODE) {
            buffer.drawRectangle(x, y, width, height, new Color(255, 0, 0, 100));
        }
    }
}
