package cegepst.game.helpers;

import cegepst.engine.menu.buttons.ButtonStyle;
import cegepst.engine.menu.buttons.Callback;
import cegepst.engine.menu.buttons.RoundButton;
import cegepst.game.displays.Display;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.settings.GameSettings;

public class ButtonFactory {

    public static RoundButton playButton(int x, int y) {
        RoundButton playButton = new RoundButton(x, y, "Play");
        playButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                EventSystem.getInstance().onButtonClicked(ButtonEventType.GAME_DISPLAY);
            }
        });
        return playButton;
    }

    public static RoundButton optionButton(int x, int y) {
        RoundButton optionButton = new RoundButton(x, y, "Option");
        optionButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                EventSystem.getInstance().onButtonClicked(ButtonEventType.OPTION_MENU_DISPLAY);
            }
        });
        return optionButton;
    }

    public static RoundButton quitButton(int x, int y) {
        RoundButton quitButton = new RoundButton(x, y, "Quit");
        quitButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                EventSystem.getInstance().onButtonClicked(ButtonEventType.QUIT);
            }
        });
        return quitButton;
    }

    public static RoundButton soundButton(int x, int y) {
        RoundButton soundButton = new RoundButton(x, y,
                "Sound " + (GameSettings.SOUND ? "On" : "Off"));
        soundButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                GameSettings.SOUND = !GameSettings.SOUND;
                soundButton.setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
            }
        });
        return soundButton;
    }

    public static RoundButton musicButton(int x, int y) {
        RoundButton musicButton = new RoundButton(x, y,
                "Music " + (GameSettings.MUSIC ? "On" : "Off"));
        musicButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                GameSettings.MUSIC = !GameSettings.MUSIC;
                musicButton.setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
            }
        });
        return musicButton;
    }

    public static RoundButton debugButton(int x, int y) {
        RoundButton debugButton = new RoundButton(x, y,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
        debugButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                debugButton.setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
            }
        });
        return debugButton;
    }

    public static RoundButton backToMainMenuButton(int x, int y) {
        RoundButton backButton = new RoundButton(x, y, "Back");
        backButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                EventSystem.getInstance().onButtonClicked(ButtonEventType.MAIN_MENU_DISPLAY);
            }
        });
        return backButton;
    }

    public static RoundButton moneyCheatButton(int x, int y) {
        RoundButton backButton = new RoundButton(x, y, ButtonStyle.CHEAT_STYLE , "$");
        backButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                EventSystem.getInstance().onButtonClicked(ButtonEventType.MONEY_CHEAT);
            }
        });
        return backButton;
    }
}
