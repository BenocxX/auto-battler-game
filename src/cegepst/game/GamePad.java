package cegepst.game;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_Q;
    private int useKey = KeyEvent.VK_E;
    private int screenModeKey = KeyEvent.VK_F;
    private int debugKey = KeyEvent.VK_D;

    public GamePad() {
        bindKey(quitKey);
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
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
