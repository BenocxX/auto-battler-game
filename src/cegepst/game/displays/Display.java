package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.game.GamePad;

public abstract class Display {

    private boolean alreadyInDisplay;
    private DisplayType displayType;
    protected int currentId;

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

    protected void resetStateData(GamePad gamePad) {
        if (!alreadyInDisplay) {
            currentId = displayType.getId();
            gamePad.clearTypedKeys();
        }
    }

    protected void updateAlreadyInDisplay() {
        alreadyInDisplay = (currentId == displayType.getId());
    }
}
