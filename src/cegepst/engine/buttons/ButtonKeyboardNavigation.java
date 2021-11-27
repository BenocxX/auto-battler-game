package cegepst.engine.buttons;

import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.GamePad;

import java.util.ArrayList;

public class ButtonKeyboardNavigation {

    private GamePad gamePad;
    private ArrayList<RoundButton> buttons;
    private LoopingIndex loopingIndex;

    public ButtonKeyboardNavigation(GamePad gamePad) {
        this.gamePad = gamePad;
    }

    public void inputCheck(ArrayList<RoundButton> buttons, LoopingIndex loopingIndex) {
        this.buttons = buttons;
        this.loopingIndex = loopingIndex;
        selectKeyCheck();
        navigationKeyCheck();
    }

    private void selectKeyCheck() {
        if (gamePad.isEnterTyped()) {
            buttons.get(loopingIndex.getIndex()).callback();
        }
    }

    private void navigationKeyCheck() {
        updateIndex();
        updateSelectedButton();
    }

    private void updateIndex() {
        if (gamePad.isUpTyped()) {
            loopingIndex.decrement();
        } else if (gamePad.isDownTyped()) {
            loopingIndex.increment();
        }
    }

    private void updateSelectedButton() {
        if (gamePad.isUpTyped() || gamePad.isDownTyped()) {
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(loopingIndex.getIndex()));
            }
        }
    }
}
