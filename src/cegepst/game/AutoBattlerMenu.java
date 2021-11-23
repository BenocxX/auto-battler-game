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
        upKeyPressed();
        downKeyPressed();
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

    private void upKeyPressed() {
        if (gamePad.isUpTyped()) {
            System.out.println("Up typed");
        }
    }

    private void downKeyPressed() {
        if (gamePad.isDownTyped()) {
            System.out.println("Down typed");
        }
    }

    private void mouseHoverCheck() {
        if (inOptionMenu) {
            for (RoundButton button : optionMenuButtons) {
                button.checkIfHovered(mouse.getMousePosition());
            }
        } else {
            for (RoundButton button : mainMenuButtons) {
                button.checkIfHovered(mouse.getMousePosition());
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
    }

    private void initializeOptionMenuButtons() {
        optionMenuButtons.add(ButtonFactory.soundButton());
        optionMenuButtons.add(ButtonFactory.musicButton());
        optionMenuButtons.add(ButtonFactory.debugButton());
        optionMenuButtons.add(ButtonFactory.backButton(this));
    }
}
