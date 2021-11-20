package cegepst.game.eventsystem;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

/**
 * Implémente TriggerAreaListener afin de pouvoir réagir aux
 * events de ce type.
 */
public class Box extends StaticEntity implements TriggerAreaListener {

    private static final Color TRIGGERED_COLOR = new Color(84, 255, 0);
    private static final Color UNTRIGGERED_COLOR = new Color(255, 139, 0);;

    private int id;
    private Color color = UNTRIGGERED_COLOR;

    /**
     * Constructeur de la Box.
     */
    public Box(int id) {
        this.id = id;
        setDimension(50, 50);
        EventSystem.getInstance().addTriggerAreaListener(this);
    }

    /**
     * Appelée lorsque que le trigger est entré. On utilise
     * le id du trigger ayant déclenché cet évent afin de
     * vérifier que la Box est bien relié à ce trigger.
     */
    @Override
    public void onTriggerEnter(int triggerId) {
        if (triggerId == id) {
            color = TRIGGERED_COLOR;
        }
    }

    /**
     * Appelée durant que le trigger est déclenché. On utilise
     * le id du trigger ayant déclenché cet évent afin de
     * vérifier que la Box est bien relié à ce trigger.
     */
    @Override
    public void onTrigger(int triggerId) {
        if (triggerId == id) {
            // Code called while box is triggered
        }
    }

    /**
     * Appelée lorsque que le trigger est quitté. On utilise
     * le id du trigger ayant déclenché cet évent afin de
     * vérifier que la Box est bien relié à ce trigger.
     */
    @Override
    public void onTriggerLeave(int triggerId) {
        if (triggerId == id) {
            // Code called while box is losing trigger
            color = UNTRIGGERED_COLOR;
        }
    }

    /**
     * Draw la Box à l'écran.
     */
    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, color);
        buffer.drawCenteredText("ID: #" + id, getBounds());
    }
}
