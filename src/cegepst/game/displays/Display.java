package cegepst.game.displays;

import cegepst.engine.Buffer;

public abstract class Display {

    protected DisplayType displayType;
    protected int currentId;
    protected boolean alreadyInDisplay;

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

    public void goToPauseDisplay() {
        currentId = DisplayType.PAUSE_MENU.getId();
    }

    public void quit() {
        currentId = DisplayType.QUIT.getId();
    }

    protected void updateAlreadyInDisplay() {
        alreadyInDisplay = (currentId == displayType.getId());
    }
}
