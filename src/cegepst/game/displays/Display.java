package cegepst.game.displays;

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
