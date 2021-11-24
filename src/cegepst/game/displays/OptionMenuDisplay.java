package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.RoundButton;
import cegepst.engine.controls.MouseController;
import cegepst.game.GamePad;
import cegepst.game.entities.ButtonFactory;

import java.awt.*;
import java.util.ArrayList;

public class OptionMenuDisplay {

    private GamePad gamePad;
    private MouseController mouse;
    private ArrayList<RoundButton> buttons;
    private int selectedButtonIndex;

    // TODO: duplicate
    private boolean alreadyInDisplay = false;
    private int currentId = DisplayType.OPTION_MENU.getId();

    public OptionMenuDisplay() {
        gamePad = new GamePad();
        mouse = new MouseController();
        buttons = new ArrayList<>();
        initializeButtons();
    }

    public int update() {
        // TODO: duplicate
        if (!alreadyInDisplay) {
            resetStateData();
        }

        quitKeyCheck();
        enterKeyCheck();
        upDownKeyCheck();
        mouseHoverCheck();
        mouseClickCheck();
        gamePad.clearTypedKeys();

        // TODO: duplicate
        alreadyInDisplay = (currentId == DisplayType.OPTION_MENU.getId());

        return currentId;
    }

    public void draw(Buffer buffer) {
        buffer.setFontSize(Font.PLAIN, 20);
        for (RoundButton button : buttons) {
            button.draw(buffer);
        }
    }

    public void goToMainMenuDisplay() {
        currentId = DisplayType.MAIN_MENU.getId();
    }

    // TODO: duplicate
    private void resetStateData() {
        currentId = DisplayType.OPTION_MENU.getId();
        gamePad.clearTypedKeys();
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            goToMainMenuDisplay();
        }
    }

    private void enterKeyCheck() {
        if (gamePad.isEnterTyped()) {
            buttons.get(selectedButtonIndex).customEvent();
        }
    }

    private void upDownKeyCheck() {
        upKeyPressed();
        downKeyPressed();
    }

    private void upKeyPressed() {
        if (gamePad.isUpTyped()) {
            decrementSelectedButtonIndex();
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(selectedButtonIndex));
            }
        }
    }

    private void downKeyPressed() {
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
                button.customEvent();
                break;
            }
        }
    }

    private void initializeButtons() {
        buttons.add(ButtonFactory.soundButton());
        buttons.add(ButtonFactory.musicButton());
        buttons.add(ButtonFactory.debugButton());
        buttons.add(ButtonFactory.backButton(this));
        buttons.get(0).isSelected(true);
    }
}
