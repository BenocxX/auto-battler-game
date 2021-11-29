package cegepst.game.entities.miscellaneous;

import cegepst.engine.menu.buttons.Callback;
import cegepst.engine.menu.buttons.RoundButton;
import cegepst.game.displays.Display;
import cegepst.game.displays.MainMenuDisplay;
import cegepst.game.displays.PauseMenuDisplay;
import cegepst.game.settings.GameSettings;
import cegepst.game.displays.OptionMenuDisplay;

public class ButtonFactory {

    public static RoundButton playButton(Display menu, int x, int y) {
        RoundButton playButton = new RoundButton(x, y, "Play");
        playButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToGameDisplay();
            }
        });
        return playButton;
    }

    public static RoundButton optionButton(Display menu, int x, int y) {
        RoundButton optionButton = new RoundButton(x, y, "Option");
        optionButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToOptionDisplay();
            }
        });
        return optionButton;
    }

    public static RoundButton quitButton(Display menu, int x, int y) {
        RoundButton quitButton = new RoundButton(x, y, "Quit");
        quitButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.quit();
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

    public static RoundButton backToMainMenuButton(Display menu, int x, int y) {
        RoundButton backButton = new RoundButton(x, y, "Back");
        backButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToMainMenuDisplay();
            }
        });
        return backButton;
    }

    public static RoundButton backToGameButton(Display menu, int x, int y) {
        RoundButton backButton = new RoundButton(x, y, "Back");
        backButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToGameDisplay();
            }
        });
        return backButton;
    }

    public static RoundButton mainMenuButton(Display currentDisplay, int x, int y) {
        RoundButton mainMenuButton = new RoundButton(x, y, "Main menu");
        mainMenuButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                currentDisplay.goToMainMenuDisplay();
            }
        });
        return mainMenuButton;
    }
}
