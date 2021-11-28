package cegepst.engine.buttons;

import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.MousePad;

import java.util.ArrayList;

public class MouseNavigation extends NavigationController {

    private MousePad mousePad;

    public MouseNavigation(MousePad mousePad) {
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
            if (button.isHovered(mousePad.getPosition())) {
                unselectLastCurrentButton();
                selectHoveredButton(button);
            }
        }
    }

    private void unselectLastCurrentButton() {
        buttons.get(loopingIndex.getIndex()).isSelected(false);
    }

    private void selectHoveredButton(RoundButton button) {
        loopingIndex.setCurrentIndex(buttons.indexOf(button));
        buttons.get(loopingIndex.getIndex()).isSelected(true);
    }

    private void mouseClickCheck() {
        if (mousePad.isLeftClicked()) {
            for (RoundButton button : buttons) {
                if (button.isClicked(mousePad.getPosition())) {
                    button.callback();
                    return;
                }
            }
        }
    }
}
