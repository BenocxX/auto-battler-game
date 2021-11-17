package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.buttons.CustomEvent;
import cegepst.engine.buttons.Button;
import cegepst.engine.controls.MouseController;
import cegepst.game.AutoBattlerGame;
import cegepst.game.GamePad;
import cegepst.game.GameSettings;

import java.util.HashMap;

public class GameMenu extends Game {

    private GamePad gamePad;
    private MouseController mouse;
    private HashMap<String, Button> buttons;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        mouse = new MouseController();
        buttons = new HashMap<>();
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
            for (HashMap.Entry<String, Button> entry : buttons.entrySet()) {
                if (entry.getValue().isClicked(mouse.getMousePosition()) &&
                        entry.getValue().isVisible()) {
                    entry.getValue().event();
                    break;
                }
            }
        }
        mouse.resetIsClicked();
    }

    private void initializeMenuButtons() {
        /* TODO: Demander pour fonctions anonymes avec interface.
         *  Utilisation car une seule classe de bouton, donc impossible
         *  de redéfinir la fonction de l'interface afin d'avoir des
         *  actions différentes pour chaque boutons.
         */
        buttons.put("PlayButton", new Button(
                100, 100, 200, 50, "Play", true,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        stop();
                        (new AutoBattlerGame()).start();
                    }
                }));

        buttons.put("OptionsButton", new Button(
                100, 160, 200, 50, "Options", true,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        invertButtonsVisibility();
                    }
                }));

        buttons.put("QuitButton", new Button(
                100, 220, 200, 50, "Quit", true,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        stop();
                    }
                }));

        buttons.put("SoundButton", new Button(
                100, 100, 200, 50, "Sound " + (GameSettings.SOUND ? "On" : "Off"), false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        GameSettings.SOUND = !GameSettings.SOUND;
                        buttons.get("SoundButton").setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
                    }
                }));

        buttons.put("MusicButton", new Button(
                100, 160, 200, 50, "Music " + (GameSettings.MUSIC ? "On" : "Off"), false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        GameSettings.MUSIC = !GameSettings.MUSIC;
                        buttons.get("MusicButton").setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
                    }
                }));

        buttons.put("DebugButton", new Button(
                100, 220, 200, 50, "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"), false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                        buttons.get("DebugButton").setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
                    }
                }));

        buttons.put("BackButton", new Button(
                100, 280, 200, 50, "Back", false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        invertButtonsVisibility();
                    }
                }));
    }

    private void invertButtonsVisibility() {
        for (HashMap.Entry<String, Button> entry : buttons.entrySet()) {
            entry.getValue().setVisible(!entry.getValue().isVisible());
        }
    }
}
