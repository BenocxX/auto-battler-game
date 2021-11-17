package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.GamePad;

public class Menu extends Game {

    private GamePad gamePad;

    @Override
    public void initialize() {
        gamePad = new GamePad();
    }

    @Override
    public void update() {
        quitKeyCheck();
    }

    @Override
    public void draw(Buffer buffer) {

    }

    @Override
    public void conclude() {

    }

    private void quitKeyCheck() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }
}
