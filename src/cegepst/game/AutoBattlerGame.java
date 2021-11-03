package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.GameTime;

import java.awt.*;

public class AutoBattlerGame extends Game {

    private GamePad gamePad;
    private Player player;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
    }

    @Override
    public void draw(Buffer buffer) {
        player.draw(buffer);
        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedTimeFormattedTime(), 10, 40, Color.WHITE);
    }

    @Override
    public void conclude() {

    }
}
