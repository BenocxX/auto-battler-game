package cegepst.game;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;

public class GamePad extends MovementController {

    private int quitKey = KeyEvent.VK_Q;
    private int useKey = KeyEvent.VK_F;
    private int debugKey = KeyEvent.VK_D;

    public GamePad() {
        bindKey(quitKey);
        bindKey(useKey);
        RenderingEngine.getInstance().addKeyListener(this);
    }

    public boolean isQuitPressed() {
        return isKeyPressed(quitKey);
    }

    public boolean isUseTyped() {
        return isKeyTyped(useKey);
    }

    public boolean isDebugTyped() {
        return isKeyTyped(debugKey);
    }
}
