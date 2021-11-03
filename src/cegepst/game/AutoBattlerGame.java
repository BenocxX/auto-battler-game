package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.GameTime;

import java.awt.*;

public class AutoBattlerGame extends Game {

    private GamePad gamePad;

    @Override
    public void initialize() {
        gamePad = new GamePad();
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedTimeFormattedTime(), 10, 40, Color.WHITE);
    }

    @Override
    public void conclude() {

    }
}
