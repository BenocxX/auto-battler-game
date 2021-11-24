package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.game.*;
import cegepst.game.entities.BuyStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.TriggerArea;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplay extends Display {

    private GamePad gamePad;
    private Player player;
    private World world;
    private Initializer initializer;
    private ArrayList<BuyStation> buyStations;
    private ArrayList<TriggerArea> triggerAreas;

    public GameDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        player = new Player(gamePad);
        world = new World();
        initializer = new Initializer();
        buyStations = initializer.getBuyStations();
        triggerAreas = initializer.getTriggerAreasForBuyStations(buyStations);
    }

    @Override
    public int update() {
        resetStateData(gamePad);
        keysInputCheck();
        player.update();
        for (TriggerArea triggerArea : triggerAreas) {
            triggerArea.triggerCheck(player);
        }
        gamePad.clearTypedKeys();
        updateAlreadyInDisplay();
        return currentId;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.setFontSize(Font.PLAIN, 14);
        logicDraw(buffer);
        UIDraw(buffer);
    }

    private void logicDraw(Buffer buffer) {
        world.draw(buffer);
        for (BuyStation buyStation : buyStations) {
            buyStation.draw(buffer);
        }
        for (TriggerArea triggerArea : triggerAreas) {
            triggerArea.draw(buffer);
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

    private void keysInputCheck() {
        quitKeyCheck();
        debugKeyCheck();
        useKeyCheck();
        screenModeKeyCheck();
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            goToMainMenuDisplay();
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
                if (buyStation.isSelected()) {
                    buyStation.buyItem();
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
