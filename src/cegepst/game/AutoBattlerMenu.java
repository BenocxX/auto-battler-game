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
    private ArrayList<Button> mainMenuButtons;
    private ArrayList<Button> optionMenuButtons;
    private boolean inOptionMenu = false;
    private boolean isQuitting = false;
    private boolean isStayingInMenu = true;

    public AutoBattlerMenu() {
        gamePad = new GamePad();
        mouse = new MouseController();
        mainMenuButtons = new ArrayList<>();
        optionMenuButtons = new ArrayList<>();
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
        if (inOptionMenu) {
            for (Button button : optionMenuButtons) {
                button.draw(buffer);
            }
        } else {
            for (Button button : mainMenuButtons) {
                button.draw(buffer);
            }
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
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            if (inOptionMenu) {
                inOptionMenu = false;
            } else {
                isQuitting = true;
            }
        }
    }

    private void mouseHoverCheck() {
        if (inOptionMenu) {
            for (Button button : optionMenuButtons) {
                button.checkIfHovered(mouse.getMousePosition());
            }
        } else {
            for (Button button : mainMenuButtons) {
                button.checkIfHovered(mouse.getMousePosition());
            }
        }
    }

    private void mouseClickCheck() {
        if (mouse.isClicked()) {
            if (inOptionMenu) {
                buttonClickCheck(optionMenuButtons);
            } else {
                buttonClickCheck(mainMenuButtons);
            }
        }
        mouse.resetIsClicked();
    }

    private void buttonClickCheck(ArrayList<Button> buttons) {
        for (Button button : buttons) {
            if (button.isClicked(mouse.getMousePosition())) {
                button.customEvent();
                break;
            }
        }
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
                inOptionMenu = true;
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
                "Sound " + (GameSettings.SOUND ? "On" : "Off"), true);
        soundButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.SOUND = !GameSettings.SOUND;
                soundButton.setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
            }
        });

        Button musicButton = new RoundButton(100, 160,
                "Music " + (GameSettings.MUSIC ? "On" : "Off"), true);
        musicButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.MUSIC = !GameSettings.MUSIC;
                musicButton.setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
            }
        });

        Button debugButton = new RoundButton(100, 220,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"), true);
        debugButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                debugButton.setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
            }
        });

        Button backButton = new RoundButton(100, 280, "Back", true);
        backButton.setCustomEvent(new CustomEvent() {
            @Override
            public void event() {
                inOptionMenu = false;
            }
        });

        mainMenuButtons.add(playButton);
        mainMenuButtons.add(optionButton);
        mainMenuButtons.add(quitButton);
        optionMenuButtons.add(soundButton);
        optionMenuButtons.add(musicButton);
        optionMenuButtons.add(debugButton);
        optionMenuButtons.add(backButton);
    }
}
