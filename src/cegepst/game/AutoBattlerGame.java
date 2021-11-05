package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.GameTime;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoBattlerGame extends Game {

    private GamePad gamePad;
    private Player player;
    private ArrayList<BuyStation> buyStations;
    private HashMap<Trigger, Triggerable> triggers;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);

        buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(100, 100));
        buyStations.add(new BuyStation(200, 100));
        buyStations.add(new BuyStation(300, 100));
        buyStations.add(new BuyStation(400, 100));

        triggers = new HashMap<>();
        for (BuyStation buyStation : buyStations) {
            Trigger trigger = new Trigger();
            trigger.teleport(buyStation.getX() - 10, buyStation.getY() + 10);
            trigger.setDimension(50, 50);
            triggers.put(trigger, buyStation);
        }
    }

    @Override
    public void update() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        player.update();

        for (HashMap.Entry<Trigger, Triggerable> entry : triggers.entrySet()) {
            if (entry.getKey().isTriggered(player)) {
                entry.getValue().trigger();
            } else {
                entry.getValue().untrigger();
            }
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
