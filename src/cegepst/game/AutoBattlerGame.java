package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

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
        initBuyStations();
        initTriggersHashMap();
    }

    @Override
    public void update() {
        quitCheck();
        player.update();
        checkTriggers();
    }

    @Override
    public void draw(Buffer buffer) {
        for (BuyStation buyStation : buyStations) {
            buyStation.draw(buffer);
        }
        for (Trigger triggers: triggers.keySet()) {
            triggers.draw(buffer);
        }
        player.draw(buffer);
        buffer.drawGameDebugStats();
    }

    @Override
    public void conclude() {

    }

    private void quitCheck() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }

    private void checkTriggers() {
        for (HashMap.Entry<Trigger, Triggerable> entry : triggers.entrySet()) {
            if (entry.getKey().isTriggered(player)) {
                entry.getValue().trigger();
            } else {
                entry.getValue().untrigger();
            }
        }
    }

    private void initBuyStations() {
        buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(100, 100));
        buyStations.add(new BuyStation(200, 100));
        buyStations.add(new BuyStation(300, 100));
        buyStations.add(new BuyStation(400, 100));
    }

    private void initTriggersHashMap() {
        triggers = new HashMap<>();
        for (BuyStation buyStation : buyStations) {
            Trigger trigger = new Trigger();
            trigger.teleport(buyStation.getX() - 10, buyStation.getY() + buyStation.getHeight() + 10);
            trigger.setDimension(50, 50);
            triggers.put(trigger, buyStation);
        }
    }
}
