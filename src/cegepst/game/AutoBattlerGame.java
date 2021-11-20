package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.triggers.Trigger;
import cegepst.engine.triggers.TriggerRepository;
import cegepst.game.entities.BuyStation;
import cegepst.game.entities.Player;
import cegepst.game.eventsystem.Box;
import cegepst.game.eventsystem.TriggerArea;

import java.awt.*;
import java.util.ArrayList;

public class AutoBattlerGame {

    private GamePad gamePad;
    private Player player;
    private Initializer initializer;
    private ArrayList<BuyStation> buyStations;
    private TriggerRepository triggerRepository;
    private boolean isStayingInGame = true;

    // TODO: Test for Event System
    private TriggerArea triggerArea1;
    private TriggerArea triggerArea2;
    private Box box1;
    private Box box2;

    public AutoBattlerGame() {
        gamePad = new GamePad();
        player = new Player(gamePad);
        initializer = new Initializer();
        triggerRepository = new TriggerRepository();
        buyStations = initializer.getBuyStations();
        triggerRepository.addEntries(initializer.getTriggersForBuyStations(buyStations));
        GameSettings.isFullscreenMode = false;
        Sound.MUSIC.playLoop(GameSettings.MUSIC);

        // TODO: Test for Event System
        triggerArea1 = new TriggerArea(1);
        triggerArea1.setDimension(50, 50);
        triggerArea1.teleport(200, 400);

        triggerArea2 = new TriggerArea(2);
        triggerArea2.setDimension(50, 50);
        triggerArea2.teleport(400, 400);

        box1 = new Box(1);
        box1.teleport(200, 300);

        box2 = new Box(2);
        box2.teleport(400, 300);
    }

    public boolean update() {
        quitKeyCheck();
        debugKeyCheck();
        useKeyCheck();
        screenModeKeyCheck();
        player.update();
        player.isTriggering(triggerRepository);
        gamePad.clearTypedKeys();

        // TODO: Test for Event System
        triggerArea1.triggerCheck(player);
        triggerArea2.triggerCheck(player);

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
        // TODO: Test for Event System
        triggerArea1.draw(buffer);
        triggerArea2.draw(buffer);
        box1.draw(buffer);
        box2.draw(buffer);

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
