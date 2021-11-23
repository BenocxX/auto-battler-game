package cegepst.game.entities;

import cegepst.engine.buttons.CustomEvent;
import cegepst.engine.buttons.RoundButton;
import cegepst.game.AutoBattlerMenu;
import cegepst.game.GameSettings;

public class ButtonFactory {

    public static RoundButton playButton(AutoBattlerMenu menu) {
        RoundButton playButton = new RoundButton(100, 100, "Play", true);
        playButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                menu.setIsStayingInMenu(false);
            }
        });
        return playButton;
    }

    public static RoundButton optionButton(AutoBattlerMenu menu) {
        RoundButton optionButton = new RoundButton(100, 160, "Option", true);
        optionButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                menu.setInOptionMenu(true);
            }
        });
        return optionButton;
    }

    public static RoundButton quitButton(AutoBattlerMenu menu) {
        RoundButton quitButton = new RoundButton(100, 220, "Quit", true);
        quitButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                menu.setIsQuitting(true);
            }
        });
        return quitButton;
    }

    public static RoundButton soundButton() {
        RoundButton soundButton = new RoundButton(100, 100,
                "Sound " + (GameSettings.SOUND ? "On" : "Off"), true);
        soundButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.SOUND = !GameSettings.SOUND;
                soundButton.setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
            }
        });
        return soundButton;
    }

    public static RoundButton musicButton() {
        RoundButton musicButton = new RoundButton(100, 160,
                "Music " + (GameSettings.MUSIC ? "On" : "Off"), true);
        musicButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.MUSIC = !GameSettings.MUSIC;
                musicButton.setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
            }
        });
        return musicButton;
    }

    public static RoundButton debugButton() {
        RoundButton debugButton = new RoundButton(100, 220,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"), true);
        debugButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                debugButton.setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
            }
        });
        return debugButton;
    }

    public static RoundButton backButton(AutoBattlerMenu menu) {
        RoundButton backButton = new RoundButton(100, 280, "Back", true);
        backButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                menu.setInOptionMenu(false);
            }
        });
        return backButton;
    }
}
