package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.RoundButton;
import cegepst.engine.controls.MouseController;
import cegepst.game.GamePad;
import cegepst.game.entities.ButtonFactory;

import java.awt.*;
import java.util.ArrayList;

public class MainMenuDisplay {

    private GamePad gamePad;
    private MouseController mouse;
    private ArrayList<RoundButton> buttons;
    private int selectedButtonIndex;

    private boolean alreadyInDisplay = false;
    private int currentId = DisplayIds.MAIN_MENU.getId();

    public MainMenuDisplay() {
        gamePad = new GamePad();
        mouse = new MouseController();
        buttons = new ArrayList<>();
        initializeButtons();
    }

    public int update() {
        if (!alreadyInDisplay) {
            resetStateData();
        }
        quitKeyCheck();
        enterKeyCheck();
        upDownKeyCheck();
        mouseHoverCheck();
        mouseClickCheck();
        gamePad.clearTypedKeys();
        alreadyInDisplay = (currentId == DisplayIds.MAIN_MENU.getId());
        return currentId;
    }

    public void draw(Buffer buffer) {
        buffer.setFontSize(Font.PLAIN, 20);
        for (RoundButton button : buttons) {
            button.draw(buffer);
        }
    }

    private void resetStateData() {
        currentId = DisplayIds.MAIN_MENU.getId();
        gamePad.clearTypedKeys();
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            quit();
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
        buttonHoverCheck();
    }

    private void buttonHoverCheck() {
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

    public void goToGameDisplay() {
        currentId = DisplayIds.GAME.getId();
    }

    public void goToOptionDisplay() {
        currentId = DisplayIds.OPTION_MENU.getId();
    }

    public void quit() {
        currentId = DisplayIds.QUIT.getId();
    }

    private void initializeButtons() {
        buttons.add(ButtonFactory.playButton(this));
        buttons.add(ButtonFactory.optionButton(this));
        buttons.add(ButtonFactory.quitButton(this));
        buttons.get(0).isSelected(true);
    }
}
