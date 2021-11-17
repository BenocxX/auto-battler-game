package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.controls.MouseController;
import cegepst.game.AutoBattlerGame;
import cegepst.game.GamePad;
import cegepst.game.GameSettings;

import java.util.HashMap;

public class GameMenu extends Game {

    private GamePad gamePad;
    private MouseController mouse;
    private HashMap<String, Button> buttons;
    private boolean isMainMenu;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        mouse = new MouseController();
        buttons = new HashMap<>();
        isMainMenu = true;
        initializeMenuButtons();
    }

    @Override
    public void update() {
        quitCheck();
        mouseHoverCheck();
        mouseClickCheck();
    }

    @Override
    public void draw(Buffer buffer) {
        for (HashMap.Entry<String, Button> entry : buttons.entrySet()) {
            entry.getValue().draw(buffer);
        }
    }

    @Override
    public void conclude() {

    }

    private void quitCheck() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }

    private void mouseHoverCheck() {
        for (HashMap.Entry<String, Button> entry : buttons.entrySet()) {
            entry.getValue().checkIfHovered(mouse.getMousePosition());
        }
    }

    private void mouseClickCheck() {
        if (mouse.isClicked()) {
            if (isMainMenu) {
                if (buttons.get("PlayButton").isClicked(mouse.getMousePosition())) {
                    stop();
                    (new AutoBattlerGame()).start();
                } else if (buttons.get("OptionsButton").isClicked(mouse.getMousePosition())) {
                    buttons.clear();
                    initializeOptionsButtons();
                    isMainMenu = false;
                } else if (buttons.get("QuitButton").isClicked(mouse.getMousePosition())) {
                    stop();
                }
            } else {
                if (buttons.get("SoundButton").isClicked(mouse.getMousePosition())) {
                    GameSettings.SOUND = !GameSettings.SOUND;
                    buttons.get("SoundButton").setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
                } else if (buttons.get("MusicButton").isClicked(mouse.getMousePosition())) {
                    GameSettings.MUSIC = !GameSettings.MUSIC;
                    buttons.get("MusicButton").setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
                } else if (buttons.get("DebugButton").isClicked(mouse.getMousePosition())) {
                    GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                    buttons.get("DebugButton").setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
                } else if (buttons.get("BackButton").isClicked(mouse.getMousePosition())) {
                    buttons.clear();
                    initializeMenuButtons();
                    isMainMenu = true;
                }
            }
        }
        mouse.resetIsClicked();
    }

    private void initializeMenuButtons() {
        buttons.put("PlayButton", new Button(
                100, 100, 200, 50,
                "Play"));
        buttons.put("OptionsButton", new Button(
                100, 160, 200, 50,
                "Options"));
        buttons.put("QuitButton", new Button(
                100, 220, 200, 50,
                "Quit"));
    }

    private void initializeOptionsButtons() {
        buttons.put("SoundButton", new Button(
                100, 100, 200, 50,
                "Sound " + (GameSettings.SOUND ? "On" : "Off")));
        buttons.put("MusicButton", new Button(
                100, 160, 200, 50,
                "Music " + (GameSettings.MUSIC ? "On" : "Off")));
        buttons.put("DebugButton", new Button(
                100, 220, 200, 50,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off")));
        buttons.put("BackButton", new Button(
                100, 280, 200, 50,
                "Back"));
    }
}
