package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.RoundButton;
import cegepst.engine.controls.MouseController;
import cegepst.game.entities.ButtonFactory;

import java.util.ArrayList;

public class AutoBattlerMenu {

    private GamePad gamePad;
    private MouseController mouse;
    private ArrayList<RoundButton> mainMenuButtons;
    private ArrayList<RoundButton> optionMenuButtons;
    private int selectedOptionMenuButtonIndex;
    private int selectedMainMenuButtonIndex;
    private boolean inOptionMenu = false;
    private boolean isQuitting = false;
    private boolean isStayingInMenu = true;

    public AutoBattlerMenu() {
        gamePad = new GamePad();
        mouse = new MouseController();
        mainMenuButtons = new ArrayList<>();
        optionMenuButtons = new ArrayList<>();
        initializeMainMenuButtons();
        initializeOptionMenuButtons();
    }

    public boolean update() {
        quitKeyCheck();
        enterKeyCheck();
        upDownKeyCheck();
        mouseHoverCheck();
        mouseClickCheck();
        gamePad.clearTypedKeys();
        return isStayingInMenu;
    }

    public void draw(Buffer buffer) {
        if (inOptionMenu) {
            for (RoundButton button : optionMenuButtons) {
                button.draw(buffer);
            }
        } else {
            for (RoundButton button : mainMenuButtons) {
                button.draw(buffer);
            }
        }
    }

    public boolean isQuitting() {
        return isQuitting;
    }

    public void resetStateData() {
        isQuitting = false;
        isStayingInMenu = true;
        gamePad.clearTypedKeys();
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            if (inOptionMenu) {
                inOptionMenu = false;
            } else {
                isQuitting = true;
            }
        }
    }

    // TODO: Faire touche "enter" pour click bouton
    private void enterKeyCheck() {
        if (gamePad.isEnterTyped()) {
            if (inOptionMenu) {
                optionMenuButtons.get(selectedOptionMenuButtonIndex).customEvent();
            } else {
                mainMenuButtons.get(selectedMainMenuButtonIndex).customEvent();
            }
        }
    }

    private void upDownKeyCheck() {
        if (inOptionMenu) {
            selectedOptionMenuButtonIndex = upKeyPressed(optionMenuButtons, selectedOptionMenuButtonIndex);
            selectedOptionMenuButtonIndex = downKeyPressed(optionMenuButtons, selectedOptionMenuButtonIndex);
        } else {
            selectedMainMenuButtonIndex = upKeyPressed(mainMenuButtons, selectedMainMenuButtonIndex);
            selectedMainMenuButtonIndex = downKeyPressed(mainMenuButtons, selectedMainMenuButtonIndex);
        }
    }

    private int upKeyPressed(ArrayList<RoundButton> buttons, int index) {
        if (gamePad.isUpTyped()) {
            index = decrementSelectedButtonIndex(buttons, index);
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(index));
            }
        }
        return index;
    }

    private int downKeyPressed(ArrayList<RoundButton> buttons, int index) {
        if (gamePad.isDownTyped()) {
            index = incrementSelectedButtonIndex(buttons, index);
            for (RoundButton button : buttons) {
                button.isSelected(button == buttons.get(index));
            }
        }
        return index;
    }

    private int decrementSelectedButtonIndex(ArrayList<RoundButton> buttons, int index) {
        if (index > 0) {
            index--;
        } else {
            index = buttons.size() - 1;
        }
        return index;
    }

    private int incrementSelectedButtonIndex(ArrayList<RoundButton> buttons, int index) {
        if (index < buttons.size() - 1) {
            index++;
        } else {
            index = 0;
        }
        return index;
    }

    private void mouseHoverCheck() {
        if (inOptionMenu) {
            buttonHoverCheck(optionMenuButtons);
        } else {
            buttonHoverCheck(mainMenuButtons);
        }
    }

    private void buttonHoverCheck(ArrayList<RoundButton> buttons) {
        for (RoundButton button : buttons) {
            button.checkIfHovered(mouse.getMousePosition());
            if (button.isHovered()) {
                buttons.get(inOptionMenu ? selectedOptionMenuButtonIndex :
                        selectedMainMenuButtonIndex).isSelected(false);
            }
        }
    }

    private void mouseClickCheck() {
        if (mouse.isClicked()) {
            if (inOptionMenu) {
                buttonClickCheck(optionMenuButtons);
            } else {
                buttonClickCheck(mainMenuButtons);
            }
        }
        mouse.resetIsClicked();
    }

    private void buttonClickCheck(ArrayList<RoundButton> buttons) {
        for (RoundButton button : buttons) {
            if (button.isClicked(mouse.getMousePosition())) {
                button.customEvent();
                break;
            }
        }
    }

    public void setIsStayingInMenu(boolean isStayingInMenu) {
        this.isStayingInMenu = isStayingInMenu;
    }

    public void setIsQuitting(boolean isQuitting) {
        this.isQuitting = isQuitting;
    }

    public void setInOptionMenu(boolean inOptionMenu) {
        this.inOptionMenu = inOptionMenu;
    }

    private void initializeMainMenuButtons() {
        mainMenuButtons.add(ButtonFactory.playButton(this));
        mainMenuButtons.add(ButtonFactory.optionButton(this));
        mainMenuButtons.add(ButtonFactory.quitButton(this));
        mainMenuButtons.get(0).isSelected(true);
    }

    private void initializeOptionMenuButtons() {
        optionMenuButtons.add(ButtonFactory.soundButton());
        optionMenuButtons.add(ButtonFactory.musicButton());
        optionMenuButtons.add(ButtonFactory.debugButton());
        optionMenuButtons.add(ButtonFactory.backButton(this));
        optionMenuButtons.get(0).isSelected(true);
    }
}
