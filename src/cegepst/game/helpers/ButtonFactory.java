package cegepst.game.helpers;

import cegepst.engine.menu.buttons.Callback;
import cegepst.engine.menu.buttons.RoundButton;
import cegepst.game.displays.Display;
import cegepst.game.entities.plants.Plant;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.inventory.Inventory;
import cegepst.game.settings.GameSettings;

public class ButtonFactory {

    public static RoundButton playButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Play");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.GAME_DISPLAY));
        return button;
    }

    public static RoundButton backToGame(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Back to game");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.GAME_DISPLAY));
        return button;
    }

    public static RoundButton backToMainMenu(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Back to main menu");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.MAIN_MENU_DISPLAY));
        return button;
    }

    public static RoundButton optionButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Option");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.OPTION_MENU_DISPLAY));
        return button;
    }

    public static RoundButton quitButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Quit");
        button.setCustomEvent(() ->
            EventSystem.getInstance().onButtonClicked(ButtonEventType.QUIT));
        return button;
    }

    public static RoundButton soundButton(int x, int y) {
        RoundButton button = new RoundButton(x, y,
                "Sound " + (GameSettings.SOUND ? "On" : "Off"));
        button.setCustomEvent(() -> {
            GameSettings.SOUND = !GameSettings.SOUND;
            button.setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
        });
        return button;
    }

    public static RoundButton musicButton(int x, int y) {
        RoundButton button = new RoundButton(x, y,
                "Music " + (GameSettings.MUSIC ? "On" : "Off"));
        button.setCustomEvent(() -> {
            GameSettings.MUSIC = !GameSettings.MUSIC;
            button.setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
        });
        return button;
    }

    public static RoundButton debugButton(int x, int y) {
        RoundButton button = new RoundButton(x, y,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
        button.setCustomEvent(() -> {
            GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
            button.setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
        });
        return button;
    }

    public static RoundButton backToMainMenuButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Back");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.MAIN_MENU_DISPLAY));
        return button;
    }

    public static RoundButton backToGameButton(int x, int y, Callback callback) {
        RoundButton button = new RoundButton(x, y, "Back");
        button.setCustomEvent(callback);
        return button;
    }

    public static RoundButton battleButton(int x, int y) {
        return new RoundButton(x, y,
                125, 50, 20, 20, "Battle",
                () -> EventSystem.getInstance().onButtonClicked(ButtonEventType.BATTLE));
    }

    public static RoundButton leaveBattleButton(int x, int y) {
        return new RoundButton(x, y,
                125, 50, 20, 20, "Leave Battle",
                () -> EventSystem.getInstance().onButtonClicked(ButtonEventType.LEAVE_BATTLE));
    }

    public static RoundButton inventoryButton(int x, int y, Display parent) {
        return new RoundButton(x, y,
                125, 50, 20, 20, "Inventory", () -> {
                    EventSystem.getInstance().onButtonClicked(ButtonEventType.INVENTORY);
                    EventSystem.getInstance().onInventoryOpening(parent);
                });
    }

    public static RoundButton selectPlantButton(int x, int y, Plant plant) {
        RoundButton button = new RoundButton(x, y, 14,"Select");
        button.setCustomEvent(() -> {
            if (button.getText().equalsIgnoreCase("Select")) {
                Inventory.getInstance().unselectedSelectedSlot();
                button.setText("Selected");
                EventSystem.getInstance().onSlotSelection(plant);
            } else {
                Inventory.getInstance().unselectEverySlot();
                button.setText("Select");
                EventSystem.getInstance().onSlotDeselection(plant);
            }
        });
        return button;
    }

    public static RoundButton previousPageButton(int x, int y) {
        return new RoundButton(x, y, 50, 50, 20,
                20, "<", () -> Inventory.getInstance().previousPage());
    }

    public static RoundButton nextPageButton(int x, int y) {
        return new RoundButton(x, y, 50, 50, 20,
                20, ">", () -> Inventory.getInstance().nextPage());
    }
}
