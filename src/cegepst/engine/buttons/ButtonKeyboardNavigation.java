package cegepst.engine.buttons;

import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.GamePad;

import java.util.ArrayList;

public class ButtonKeyboardNavigation {

    private GamePad gamePad;
    private LoopingIndex loopingIndex;
    private ArrayList<RoundButton> buttons;

    public ButtonKeyboardNavigation(GamePad gamePad) {
        this.gamePad = gamePad;
        loopingIndex = new LoopingIndex();
    }

    public void setButtons(ArrayList<RoundButton> buttons) {
        this.buttons = buttons;
        loopingIndex.setMaxIndex(buttons.size() - 1);
    }

    public void inputCheck() {
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
