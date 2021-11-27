package cegepst.engine.buttons;

import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.MousePad;

import java.util.ArrayList;

public class ButtonMouseNavigation extends ButtonNavigationController {

    private MousePad mousePad;

    public ButtonMouseNavigation(MousePad mousePad) {
        this.mousePad = mousePad;
    }

    @Override
    public void inputCheck(ArrayList<RoundButton> buttons, LoopingIndex loopingIndex) {
        this.buttons = buttons;
        this.loopingIndex = loopingIndex;
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
