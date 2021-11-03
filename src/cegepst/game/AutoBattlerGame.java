package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.GameTime;

import java.awt.*;
import java.util.ArrayList;

public class AutoBattlerGame extends Game {

    private GamePad gamePad;
    private Player player;
    private ArrayList<BuyStation> buyStations;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(100, 100));
        buyStations.add(new BuyStation(200, 100));
        buyStations.add(new BuyStation(300, 100));
        buyStations.add(new BuyStation(400, 100));
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();
        for (BuyStation buyStation : buyStations) {
            buyStation.update();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (BuyStation buyStation : buyStations) {
            buyStation.draw(buffer);
        }
        player.draw(buffer);
        buffer.drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        buffer.drawText(GameTime.getElapsedTimeFormattedTime(), 10, 40, Color.WHITE);
    }

    @Override
    public void conclude() {

    }
}
