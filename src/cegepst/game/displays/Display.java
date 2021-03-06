package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.eventsystem.events.ButtonListener;

public abstract class Display implements ButtonListener {

    protected DisplayType displayType;
    protected int currentId;
    protected boolean alreadyInDisplay;

    public Display(DisplayType displayType) {
        this.displayType = displayType;
        alreadyInDisplay = false;
        currentId = displayType.getId();
        EventSystem.getInstance().addButtonListener(this);
    }

    public abstract int update();
    public abstract void draw(Buffer buffer);

    @Override
    public void onButtonClick(ButtonEventType eventType) {
        if (ButtonEventType.MAIN_MENU_DISPLAY == eventType) {
            goToMainMenuDisplay();
        } else if (ButtonEventType.OPTION_MENU_DISPLAY == eventType) {
            goToOptionDisplay();
        } else if (ButtonEventType.SHOP_DISPLAY == eventType) {
            goToShopDisplay();
        } else if (ButtonEventType.INVENTORY == eventType) {
            goToInventoryDisplay();
        } else if (ButtonEventType.QUIT == eventType) {
            quit();
        }
    }

    public int getId() {
        return displayType.getId();
    }

    protected void goToMainMenuDisplay() {
        currentId = DisplayType.MAIN_MENU.getId();
    }

    protected void goToOptionDisplay() {
        currentId = DisplayType.OPTION_MENU.getId();
    }

    protected void goToShopDisplay() {
        currentId = DisplayType.SHOP.getId();
    }

    protected void goToInventoryDisplay() {
        currentId = DisplayType.INVENTORY.getId();
    }

    protected void goToGameOverDisplay() {
        currentId = DisplayType.GAME_OVER.getId();
    }

    protected void goToBattleDisplay() {
        currentId = DisplayType.BATTLE.getId();
    }

    protected void quit() {
        currentId = DisplayType.QUIT.getId();
    }

    protected void updateAlreadyInDisplay() {
        alreadyInDisplay = (currentId == displayType.getId());
    }
}
