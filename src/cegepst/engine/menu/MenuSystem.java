package cegepst.engine.menu;

import cegepst.engine.Buffer;
import cegepst.engine.menu.buttons.RoundButton;
import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;

import java.util.ArrayList;

public class MenuSystem {

    private ArrayList<RoundButton> buttons;
    private LoopingIndex loopingIndex;
    private KeyboardNavigation keyboardNavigation;
    private MouseNavigation mouseNavigation;

    public MenuSystem() {
        buttons = new ArrayList<>();
        loopingIndex = new LoopingIndex();
    }

    public void update() {
        if (keyboardNavigation != null) {
            keyboardNavigation.inputCheck(buttons, loopingIndex);
        }
        if (mouseNavigation != null) {
            mouseNavigation.inputCheck(buttons, loopingIndex);
        }
    }

    public void draw(Buffer buffer) {
        for (RoundButton button : buttons) {
            button.draw(buffer);
        }
    }

    public void addGamePadDevice(GamePad gamePad) {
        keyboardNavigation = new KeyboardNavigation(gamePad);
    }

    public void addMousePadDevice(MousePad mousePad) {
        mouseNavigation = new MouseNavigation(mousePad);
    }

    public void addButton(RoundButton button) {
        buttons.add(button);
        loopingIndex.setMaxIndex(buttons.size() - 1);
    }

    public void removeButton(RoundButton button) {
        buttons.remove(button);
        loopingIndex.setMaxIndex(buttons.size() - 1);
    }

    public RoundButton getButton(int index) {
        return buttons.get(index);
    }
}
