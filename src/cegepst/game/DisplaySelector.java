package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

public class DisplaySelector extends Game {

    private GameDisplay gameDisplay;
    private MenuDisplay menuDisplay;
    private boolean inMenu = true;

    @Override
    public void initialize() {
        gameDisplay = new GameDisplay();
        menuDisplay = new MenuDisplay();
    }

    @Override
    public void update() {
        if (inMenu) {
            inMenu = menuDisplay.update();
            stopCheck();
            gameDisplay.resetStateData();
        } else {
            inMenu = !gameDisplay.update();
            menuDisplay.resetStateData();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (inMenu) {
            menuDisplay.draw(buffer);
        } else {
            gameDisplay.draw(buffer);
        }
    }

    @Override
    public void conclude() {

    }

    private void stopCheck() {
        if (menuDisplay.isQuitting()) {
            stop();
        }
    }
}
