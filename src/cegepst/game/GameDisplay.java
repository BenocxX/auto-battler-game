package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.triggers.Trigger;
import cegepst.engine.triggers.TriggerRepository;
import cegepst.game.entities.BuyStation;
import cegepst.game.entities.Player;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplay {

    private GamePad gamePad;
    private Player player;
    private Initializer initializer;
    private ArrayList<BuyStation> buyStations;
    private TriggerRepository triggerRepository;
    private boolean isStayingInGame = true;

    public GameDisplay() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        initializer = new Initializer();
        triggerRepository = new TriggerRepository();
        buyStations = initializer.getBuyStations();
        triggerRepository.addEntries(initializer.getTriggersForBuyStations(buyStations));
        GameSettings.isFullscreenMode = false;
        Sound.MUSIC.playLoop(GameSettings.MUSIC);
    }

    public boolean update() {
        quitKeyCheck();
        debugKeyCheck();
        useKeyCheck();
        screenModeKeyCheck();
        player.update();
        player.isTriggering(triggerRepository);
        gamePad.clearTypedKeys();
        return isStayingInGame;
    }

    public void draw(Buffer buffer) {
        logicDraw(buffer);
        UIDraw(buffer);
    }

    public void resetStateData() {
        isStayingInGame = true;
        gamePad.clearTypedKeys();
    }

    private void logicDraw(Buffer buffer) {
        for (BuyStation buyStation : buyStations) {
            buyStation.draw(buffer);
        }
        for (Trigger trigger : triggerRepository.getKeys()) {
            trigger.draw(buffer);
        }
        player.draw(buffer);
    }

    private void UIDraw(Buffer buffer) {
        if (GameSettings.DEBUG_MODE) {
            buffer.drawGameDebugStats();
            buffer.drawText("('D' to deactivate debug mode)", RenderingEngine.WIDTH - 200, 20, new Color(255, 255, 255));
        } else {
            buffer.drawText("('D' to activate debug mode)", RenderingEngine.WIDTH - 184, 20, new Color(255, 255, 255));
        }
    }

    private void quitKeyCheck() {
        isStayingInGame = !gamePad.isQuitTyped();
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

    private void screenModeKeyCheck() {
        if (gamePad.isScreenModeTyped()) {
            RenderingEngine.getInstance().getScreen().toggleFullscreen();
        }
    }
}
