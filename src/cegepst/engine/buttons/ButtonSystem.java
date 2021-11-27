package cegepst.engine.buttons;

import cegepst.engine.Buffer;
import cegepst.engine.helpers.LoopingIndex;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;

import java.util.ArrayList;

public class ButtonSystem {

    private GamePad gamePad;
    private MousePad mousePad;
    private ArrayList<RoundButton> buttons;
    private LoopingIndex loopingIndex;

    public ButtonSystem() {
        buttons = new ArrayList<>();
        loopingIndex = new LoopingIndex();
    }

    public void update() {
        if (gamePad != null) {
            gamePadCheck();
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
        this.gamePad = gamePad;
    }

    public void addMouseDevice(MousePad mousePad) {
        this.mousePad = mousePad;
    }

    public void addButton(RoundButton button) {
        buttons.add(button);
        loopingIndex.setMaxIndex(buttons.size() - 1);
    }

    public void removeButton(RoundButton button) {
        buttons.remove(button);
    }

    public RoundButton getButton(int index) {
        return buttons.get(index);
    }

    private void gamePadCheck() {
        enterKeyCheck();
        arrowKeyCheck();
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

    private void enterKeyCheck() {
        if (gamePad.isEnterTyped()) {
            buttons.get(loopingIndex.getIndex()).callback();
        }
    }

    private void arrowKeyCheck() {
        upArrowCheck();
        downArrowCheck();
    }

    private void upArrowCheck() {
        if (gamePad.isUpTyped()) {
            loopingIndex.decrement();
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(loopingIndex.getIndex()));
            }
        }
    }

    private void downArrowCheck() {
        if (gamePad.isDownTyped()) {
            loopingIndex.increment();
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(loopingIndex.getIndex()));
            }
        }
    }
}
