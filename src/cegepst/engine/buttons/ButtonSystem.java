package cegepst.engine.buttons;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MouseController;
import cegepst.game.GamePad;

import java.util.ArrayList;

public class ButtonSystem {

    private GamePad gamePad;
    private MouseController mouse;
    private ArrayList<RoundButton> buttons;
    private int selectedButtonIndex;

    public ButtonSystem() {
        buttons = new ArrayList<>();
        selectedButtonIndex = 0;
    }

    public void update() {
        if (gamePad != null) {
            gamePadCheck();
        }
        if (mouse != null) {
            mouseCheck();
        }
    }

    public void draw(Buffer buffer) {
        for (RoundButton button : buttons) {
            button.draw(buffer);
        }
    }

    public void addGamePad(GamePad gamePad) {
        this.gamePad = gamePad;
    }

    public void addMouse(MouseController mouse) {
        this.mouse = mouse;
    }

    public void addButton(RoundButton button) {
        buttons.add(button);
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
            button.checkIfHovered(mouse.getMousePosition());
            if (button.isHovered()) {
                buttons.get(selectedButtonIndex).isSelected(false);
            }
        }
    }

    private void mouseClickCheck() {
        if (mouse.isClicked()) {
            buttonClickCheck();
        }
        mouse.resetIsClicked();
    }

    private void buttonClickCheck() {
        for (RoundButton button : buttons) {
            if (button.isClicked(mouse.getMousePosition())) {
                button.callback();
                break;
            }
        }
    }

    private void enterKeyCheck() {
        if (gamePad.isEnterTyped()) {
            buttons.get(selectedButtonIndex).callback();
        }
    }

    private void arrowKeyCheck() {
        upArrowCheck();
        downArrowCheck();
    }

    private void upArrowCheck() {
        if (gamePad.isUpTyped()) {
            decrementSelectedButtonIndex();
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(selectedButtonIndex));
            }
        }
    }

    private void downArrowCheck() {
        if (gamePad.isDownTyped()) {
            incrementSelectedButtonIndex();
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(selectedButtonIndex));
            }
        }
    }

    private void decrementSelectedButtonIndex() {
        if (selectedButtonIndex > 0) {
            selectedButtonIndex--;
        } else {
            selectedButtonIndex = buttons.size() - 1;
        }
    }

    private void incrementSelectedButtonIndex() {
        if (selectedButtonIndex < buttons.size() - 1) {
            selectedButtonIndex++;
        } else {
            selectedButtonIndex = 0;
        }
    }
}
