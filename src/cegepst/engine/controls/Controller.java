package cegepst.engine.controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Controller implements KeyListener {

    private final HashMap<Integer, Boolean> pressedKeys;
    private final ArrayList<Integer> typedKeys;

    public Controller() {
        pressedKeys = new HashMap<>();
        typedKeys = new ArrayList<>();
    }

    protected void bindKeys(int[] keys) {
        for (int keycode : keys) {
            pressedKeys.put(keycode, false);
        }
    }

    protected void bindKey(int key) {
        pressedKeys.put(key, false);
    }

    protected void removeKey(int key) {
        pressedKeys.remove(key);
    }

    protected boolean isKeyPressed(int key) {
        return pressedKeys.containsKey(key) && pressedKeys.get(key);
    }

    protected boolean isKeyTyped(int key) {
        return typedKeys.contains(key);
    }

    public void clearTypedKeys() {
        typedKeys.clear();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (pressedKeys.containsKey(keycode)) {
            pressedKeys.put(keycode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (pressedKeys.containsKey(keycode)) {
            pressedKeys.put(keycode, false);
        }
        typedKeys.add(keycode);
    }
}
