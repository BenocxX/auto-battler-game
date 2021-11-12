package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.RenderingEngine;
import cegepst.game.entities.BuyStation;
import cegepst.game.entities.Player;
import cegepst.engine.triggers.Trigger;
import cegepst.engine.triggers.TriggerRepository;

import java.awt.*;
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
        quitKeyCheck();
        player.update();
        player.isTriggering(triggerRepository);
        debugKeyCheck();
        useKeyCheck();
        gamePad.clearTypedKeys();
    }

    @Override
    public void draw(Buffer buffer) {
        for (BuyStation buyStation : buyStations) {
            buyStation.draw(buffer);
        }
        for (Trigger trigger : triggerRepository.getKeys()) {
            trigger.draw(buffer);
        }
        player.draw(buffer);
        if (GameSettings.DEBUG_MODE) {
            buffer.drawGameDebugStats();
            buffer.drawText("('D' to deactivate debug mode)", RenderingEngine.WIDTH - 200, 20, new Color(255, 255, 255));
        } else {
            buffer.drawText("('D' to activate debug mode)", RenderingEngine.WIDTH - 184, 20, new Color(255, 255, 255));
        }
    }

    @Override
    public void conclude() {

    }

    private void quitKeyCheck() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
    }

    private void debugKeyCheck() {
        if (gamePad.isDebugTyped()) {
            GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
        }
    }

    private void useKeyCheck() {
        if (gamePad.isUseTyped()) {
            for (BuyStation buyStation : buyStations) {
                if (triggerRepository.isValueTriggeredByTriggerer(buyStation, player)) {
                    buyStation.buy();
                }
            }
        }
    }
}
