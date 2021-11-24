package cegepst.game.entities;

import cegepst.engine.buttons.Callback;
import cegepst.engine.buttons.RoundButton;
import cegepst.game.displays.MainMenuDisplay;
import cegepst.game.GameSettings;
import cegepst.game.displays.OptionMenuDisplay;

public class ButtonFactory {

    public static RoundButton playButton(MainMenuDisplay menu) {
        RoundButton playButton = new RoundButton(100, 100, "Play");
        playButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToGameDisplay();
            }
        });
        return playButton;
    }

    public static RoundButton optionButton(MainMenuDisplay menu) {
        RoundButton optionButton = new RoundButton(100, 160, "Option");
        optionButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToOptionDisplay();
            }
        });
        return optionButton;
    }

    public static RoundButton quitButton(MainMenuDisplay menu) {
        RoundButton quitButton = new RoundButton(100, 220, "Quit");
        quitButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.quit();
            }
        });
        return quitButton;
    }

    public static RoundButton soundButton() {
        RoundButton soundButton = new RoundButton(100, 100,
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

    public static RoundButton musicButton() {
        RoundButton musicButton = new RoundButton(100, 160,
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

    public static RoundButton debugButton() {
        RoundButton debugButton = new RoundButton(100, 220,
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

    public static RoundButton backButton(OptionMenuDisplay menu) {
        RoundButton backButton = new RoundButton(100, 280, "Back");
        backButton.setCustomEvent(new Callback() {
            @Override
            public void callback() {
                menu.goToMainMenuDisplay();
            }
        });
        return backButton;
    }
}
