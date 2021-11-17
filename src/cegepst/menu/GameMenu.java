package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.buttons.ButtonStyle;
import cegepst.engine.buttons.CustomEvent;
import cegepst.engine.buttons.Button;
import cegepst.engine.buttons.RoundButton;
import cegepst.engine.controls.MouseController;
import cegepst.game.AutoBattlerGame;
import cegepst.game.GamePad;
import cegepst.game.GameSettings;

import java.util.ArrayList;

public class GameMenu extends Game {

    private GamePad gamePad;
    private MouseController mouse;
    private ArrayList<Button> buttons;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        mouse = new MouseController();
        buttons = new ArrayList<>();
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
        for (Button button : buttons) {
            button.draw(buffer);
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
        for (Button button : buttons) {
            button.checkIfHovered(mouse.getMousePosition());
        }
    }

    private void mouseClickCheck() {
        if (mouse.isClicked()) {
            for (Button button : buttons) {
                if (button.isVisible() && button.isClicked(mouse.getMousePosition())) {
                    button.event();
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
        buttons.add(new RoundButton(
                100, 100, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Play", true,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        stop();
                        (new AutoBattlerGame()).start();
                    }
                }));

        buttons.add(new RoundButton(
                100, 160, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Options", true,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        invertButtonsVisibility();
                    }
                }));

        buttons.add(new RoundButton(
                100, 220, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Quit", true,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        stop();
                    }
                }));

        buttons.add(new RoundButton(
                100, 100, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Sound " + (GameSettings.SOUND ? "On" : "Off"), false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        GameSettings.SOUND = !GameSettings.SOUND;
                        buttons.get(3).setText("Sound " + (GameSettings.SOUND ? "On" : "Off"));
                    }
                }));

        buttons.add(new RoundButton(
                100, 160, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Music " + (GameSettings.MUSIC ? "On" : "Off"), false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        GameSettings.MUSIC = !GameSettings.MUSIC;
                        buttons.get(4).setText("Music " + (GameSettings.MUSIC ? "On" : "Off"));
                    }
                }));

        buttons.add(new RoundButton(
                100, 220, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"), false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
                        buttons.get(5).setText("Debug " + (GameSettings.DEBUG_MODE ? "On" : "Off"));
                    }
                }));

        buttons.add(new RoundButton(
                100, 280, ButtonStyle.MEDIUM_HORIZONTAL_ROUND,
                "Back", false,
                new CustomEvent() {
                    @Override
                    public void activate() {
                        invertButtonsVisibility();
                    }
                }));
    }

    private void invertButtonsVisibility() {
        for (Button button : buttons) {
            button.setVisible(!button.isVisible());
        }
    }
}
