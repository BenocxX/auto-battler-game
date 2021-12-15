package cegepst.game.controls;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MovementController;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePad extends MovementController {

    private ArrayList<KeyListener> listeners;
    private int quitKey = KeyEvent.VK_Q;
    private int escapeKey = KeyEvent.VK_ESCAPE;
    private int enterKey = KeyEvent.VK_ENTER;
    private int rollKey = KeyEvent.VK_R;
    private int useKey = KeyEvent.VK_E;
    private int screenModeKey = KeyEvent.VK_F;
    private int debugKey = KeyEvent.VK_D;
    private int inventoryKey = KeyEvent.VK_I;
    private int leftKey = KeyEvent.VK_LEFT;
    private int rightKey = KeyEvent.VK_RIGHT;

    public GamePad() {
        RenderingEngine.getInstance().addKeyListener(this);
        listeners = new ArrayList<>();
    }

    public void update() {
        for (KeyListener listener : listeners) {
            listener.onKeyAction();
        }
    }

    public void addKeyListener(KeyListener listener) {
        listeners.add(listener);
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

    public boolean isInventoryTyped() {
        return isKeyTyped(inventoryKey);
    }

    public boolean isRollTyped() {
        return isKeyTyped(rollKey);
    }

    public boolean isLeftTyped() {
        return isKeyTyped(leftKey);
    }

    public boolean isRightTyped() {
        return isKeyTyped(rightKey);
    }
}
