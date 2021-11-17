package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.GamePad;

public class GameMenu extends Game {

    private GamePad gamePad;
    private Button button;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        button = new Button(100, 100, 200, 50, "Quit");
    }

    @Override
    public void update() {
        quitKeyCheck();
    }

    @Override
    public void draw(Buffer buffer) {
        button.draw(buffer);
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
