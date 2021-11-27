package cegepst.engine.buttons;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;

import java.util.ArrayList;

public class ButtonSystem {

    private MousePad mousePad;
    private ArrayList<RoundButton> buttons;
    private LoopingIndex loopingIndex;
    private ButtonKeyboardNavigation keyboardNavigation;

    public ButtonSystem() {
        buttons = new ArrayList<>();
        loopingIndex = new LoopingIndex();
    }

    public void update() {
        if (keyboardNavigation != null) {
            keyboardNavigation.inputCheck();
        }
        if (mousePad != null) {
            mouseCheck();
        }
    }

    public void draw(Buffer buffer) {
        for (RoundButton button : buttons) {
            button.draw(buffer);
        }
    }

    public void addGamePadDevice(GamePad gamePad) {
        keyboardNavigation = new ButtonKeyboardNavigation(gamePad);
    }

    public void addMouseDevice(MousePad mousePad) {
        this.mousePad = mousePad;
    }

    public void addButton(RoundButton button) {
        buttons.add(button);
        keyboardNavigation.setButtons(buttons);
        loopingIndex.setMaxIndex(buttons.size() - 1);
    }

    public void removeButton(RoundButton button) {
        buttons.remove(button);
        keyboardNavigation.setButtons(buttons);
        loopingIndex.setMaxIndex(buttons.size() - 1);
    }

    public RoundButton getButton(int index) {
        return buttons.get(index);
    }

    private void mouseCheck() {
        mouseHoverCheck();
        mouseClickCheck();
    }

    private void mouseHoverCheck() {
        for (RoundButton button : buttons) {
            button.checkIfHovered(mousePad.getPosition());
            if (button.isHovered()) {
                buttons.get(loopingIndex.getIndex()).isSelected(false);
            }
        }
    }

    private void mouseClickCheck() {
        if (mousePad.isLeftClicked()) {
            buttonClickCheck();
        }
    }

    private void buttonClickCheck() {
        for (RoundButton button : buttons) {
            if (button.isClicked(mousePad.getPosition())) {
                button.callback();
                break;
            }
        }
    }
}
