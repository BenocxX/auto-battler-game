package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.BuyStation;
import cegepst.game.entities.EntityRepository;
import cegepst.game.entities.Player;
import cegepst.game.entities.Trigger;

import java.util.ArrayList;

public class AutoBattlerGame extends Game {

    private GamePad gamePad;
    private Player player;
    private ArrayList<BuyStation> buyStations;
    private TriggerRepository triggerRepository;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        triggerRepository = new TriggerRepository();
        initBuyStations();
        initTriggersHashMap();
    }

    @Override
    public void update() {
        quitCheck();
        player.update();
        triggerRepository.triggerValuesIfCollindingWithEntity(player);
        if (gamePad.isUsePressed()) {
            for (BuyStation buyStation : buyStations) {
                if (triggerRepository.isValueTriggeredByEntity(buyStation, player)) {
                    // Item bought
                    System.out.println("Bought!");
                }
            }
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (StaticEntity entity : EntityRepository.getInstance()) {
            entity.draw(buffer);
        }
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

    private void initBuyStations() {
        buyStations = new ArrayList<>();
        buyStations.add(new BuyStation(100, 100));
        buyStations.add(new BuyStation(200, 100));
        buyStations.add(new BuyStation(300, 100));
        buyStations.add(new BuyStation(400, 100));
    }

    private void initTriggersHashMap() {
        for (BuyStation buyStation : buyStations) {
            Trigger trigger = new Trigger();
            trigger.teleport(buyStation.getX() - 10, buyStation.getY() + buyStation.getHeight() + 10);
            trigger.setDimension(50, 50);
            triggerRepository.addEntry(trigger, buyStation);
        }
    }
}
