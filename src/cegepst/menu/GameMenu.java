package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.controls.MouseController;
import cegepst.game.AutoBattlerGame;
import cegepst.game.GamePad;

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
            if (buttons.get("PlayButton").isClicked(mouse.getMousePosition())) {
                stop();
                (new AutoBattlerGame()).start();
            }
            if (buttons.get("OptionsButton").isClicked(mouse.getMousePosition())) {
                System.out.println("Options Button Clicked!");
            }
            if (buttons.get("QuitButton").isClicked(mouse.getMousePosition())) {
                stop();
            }
            mouse.resetIsClicked();
        }
    }

    private void initializeMenuButtons() {
        buttons.put("PlayButton", new Button(100, 100, 200, 50, "Play"));
        buttons.put("OptionsButton", new Button(100, 160, 200, 50, "Options"));
        buttons.put("QuitButton", new Button(100, 220, 200, 50, "Quit"));
    }
}
