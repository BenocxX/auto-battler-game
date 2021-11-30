package cegepst.engine.menu;

import cegepst.engine.menu.buttons.RoundButton;
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
        if (buttons.size() > 1) {
            for (RoundButton button : buttons) {
                if (button.isHovered(mousePad.getPosition())) {
                    unselectLastCurrentButton();
                    selectHoveredButton(button);
                }
            }
        } else {
            buttons.get(0).isSelected(buttons.get(0).isHovered(mousePad.getPosition()));
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
