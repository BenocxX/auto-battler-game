package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.CustomEvent;
import cegepst.engine.buttons.Button;
import cegepst.engine.buttons.RoundButton;
import cegepst.engine.controls.MouseController;

import java.util.ArrayList;

public class AutoBattlerMenu {

    private GamePad gamePad;
    private MouseController mouse;
    private ArrayList<Button> buttons;
    private boolean isQuitting = false;
    private boolean isStayingInMenu = true;

    public AutoBattlerMenu() {
        gamePad = new GamePad();
        mouse = new MouseController();
        buttons = new ArrayList<>();
        initializeMenuButtons();
    }

    public boolean update() {
        quitKeyCheck();
        mouseHoverCheck();
        mouseClickCheck();
        gamePad.clearTypedKeys();
        return isStayingInMenu;
    }

    public void draw(Buffer buffer) {
        for (Button button : buttons) {
            button.draw(buffer);
        }
    }

    public boolean isQuitting() {
        return isQuitting;
    }

    public void resetStateData() {
        isQuitting = false;
        isStayingInMenu = true;
        gamePad.clearTypedKeys();
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped()) {
            isQuitting = true;
        }
    }

    private void mouseHoverCheck() {
        for (Button button : buttons) {
            button.checkIfHovered(mouse.getMousePosition());
        }
    }

    private void mouseClickCheck() {
        if (mouse.isClicked()) {
            for (Button button : buttons) {
                if (button.isVisible() && button.isClicked(mouse.getMousePosition())) {
                    button.customEvent();
                    break;
                }
            }
        }
        mouse.resetIsClicked();
    }

    private void initializeMenuButtons() {
        Button playButton = new RoundButton(100, 100, "Play", true);
        playButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                isStayingInMenu = false;
            }
        });

        Button optionButton = new RoundButton(100, 160, "Option", true);
        optionButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                invertButtonsVisibility();
            }
        });

        Button quitButton = new RoundButton(100, 220, "Quit", true);
        quitButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                isQuitting = true;
            }
        });

        Button soundButton = new RoundButton(100, 100,
                "Sound " + (GameSettings.SOUND ? "On" : "Off"), false);
        soundButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.SOUND = !GameSettings.SOUND;
                soundButton.setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
            }
        });

        Button musicButton = new RoundButton(100, 160,
                "Music " + (GameSettings.MUSIC ? "On" : "Off"), false);
        musicButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.MUSIC = !GameSettings.MUSIC;
                musicButton.setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
            }
        });

        Button debugButton = new RoundButton(100, 220,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"), false);
        debugButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                debugButton.setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
            }
        });

        Button backButton = new RoundButton(100, 280, "Back", false);
        backButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                invertButtonsVisibility();
            }
        });

        buttons.add(playButton);
        buttons.add(optionButton);
        buttons.add(quitButton);
        buttons.add(soundButton);
        buttons.add(musicButton);
        buttons.add(debugButton);
        buttons.add(backButton);
    }

    private void invertButtonsVisibility() {
        for (Button button : buttons) {
            button.setVisible(!button.isVisible());
        }
    }
}
