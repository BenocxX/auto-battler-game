package cegepst.game.helpers;

import cegepst.engine.menu.buttons.ButtonStyle;
import cegepst.engine.menu.buttons.RoundButton;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.settings.GameSettings;

public class ButtonFactory {

    public static RoundButton playButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Play");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.GAME_DISPLAY));
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

    public static RoundButton backToGameButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Back");
        button.setCustomEvent(() ->
            EventSystem.getInstance().onButtonClicked(ButtonEventType.GAME_DISPLAY));
        return button;
    }

    public static RoundButton moneyCheatButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, ButtonStyle.CHEAT_STYLE , "$");
        button.setCustomEvent(() ->
            EventSystem.getInstance().onButtonClicked(ButtonEventType.MONEY_CHEAT));
        return button;
    }

    public static RoundButton battleButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Battle");
        button.setCustomEvent(() ->
                EventSystem.getInstance().onButtonClicked(ButtonEventType.BATTLE));
        return button;
    }

    public static RoundButton leaveBattleButton(int x, int y) {
        RoundButton button = new RoundButton(x, y, "Leave");
        button.setCustomEvent(() ->
            EventSystem.getInstance().onButtonClicked(ButtonEventType.LEAVE_BATTLE));
        return button;
    }

    public static RoundButton inventoryButton(int x, int y) {
        RoundButton backButton = new RoundButton(x, y, "Inventory");
        backButton.setCustomEvent(() ->
            EventSystem.getInstance().onButtonClicked(ButtonEventType.INVENTORY));
        return backButton;
    }
}
