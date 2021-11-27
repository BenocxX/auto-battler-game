package cegepst.game.controls;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_Q;
    private int escapeKey = KeyEvent.VK_ESCAPE;
    private int enterKey = KeyEvent.VK_ENTER;
    private int useKey = KeyEvent.VK_E;
    private int screenModeKey = KeyEvent.VK_F;
    private int debugKey = KeyEvent.VK_D;

    public GamePad() {
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public boolean isQuitTyped() {
        return isKeyTyped(quitKey);
    }

    public boolean isEscapeTyped() {
        return isKeyTyped(escapeKey);
    }

    public boolean isEnterTyped() {
        return isKeyTyped(enterKey);
    }

    public boolean isUseTyped() {
        return isKeyTyped(useKey);
    }

    public boolean isScreenModeTyped() {
        return isKeyTyped(screenModeKey);
    }

    public boolean isDebugTyped() {
        return isKeyTyped(debugKey);
    }
}
