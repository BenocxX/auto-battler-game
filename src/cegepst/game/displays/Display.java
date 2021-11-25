package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.controls.MouseController;
import cegepst.game.GamePad;

public abstract class Display {

    private GamePad gamePad;
    private MouseController mouse;
    private DisplayType displayType;
    protected int currentId;
    private boolean alreadyInDisplay;

    public Display(DisplayType displayType) {
        this.displayType = displayType;
        alreadyInDisplay = false;
        currentId = displayType.getId();
    }

    public abstract int update();
    public abstract void draw(Buffer buffer);

    public int getId() {
        return displayType.getId();
    }

    public void goToMainMenuDisplay() {
        currentId = DisplayType.MAIN_MENU.getId();
    }

    public void goToOptionDisplay() {
        currentId = DisplayType.OPTION_MENU.getId();
    }

    public void goToGameDisplay() {
        currentId = DisplayType.GAME.getId();
    }

    public void quit() {
        currentId = DisplayType.QUIT.getId();
    }

    protected void resetStateData() {
        if (!alreadyInDisplay) {
            currentId = displayType.getId();
            if (gamePad != null) {
                gamePad.clearTypedKeys();
            }
            if (mouse != null) {
                mouse.resetIsClicked();
            }
        }
    }

    protected void updateAlreadyInDisplay() {
        alreadyInDisplay = (currentId == displayType.getId());
    }

    protected void setGamePad(GamePad gamePad) {
        this.gamePad = gamePad;
    }

    protected void setMouse(MouseController mouse) {
        this.mouse = mouse;
    }
}
