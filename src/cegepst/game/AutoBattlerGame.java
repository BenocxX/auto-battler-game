package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.entities.StaticEntity;
import cegepst.game.entities.BuyStation;
import cegepst.game.entities.EntityRepository;
import cegepst.game.entities.Player;

import java.util.ArrayList;

public class AutoBattlerGame extends Game {

    private GamePad gamePad;
    private Player player;
    private Initializer initializer;
    private ArrayList<BuyStation> buyStations;
    private TriggerRepository triggerRepository;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        initializer = new Initializer();
        triggerRepository = new TriggerRepository();
        buyStations = initializer.getBuyStations();
        triggerRepository.addEntries(initializer.getTriggersForBuyStations(buyStations));
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
}
