package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.helpers.Initializer;
import cegepst.game.map.World;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private Player player;
    private World world;
    private Initializer initializer;
    private MenuSystem menuSystem;
    private ArrayList<ShopStation> shopStations;
    private ArrayList<TriggerArea> triggerAreas;

    public GameDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        player = new Player(gamePad);
        world = new World();
        initializer = new Initializer();
        menuSystem = new MenuSystem();
        initializeButtonSystem();
        shopStations = initializer.getShopStations();
        triggerAreas = initializer.getTriggerAreasForShopStations(shopStations);
    }

    @Override
    public int update() {
        resetStateData();
        keysInputCheck();
        menuSystem.update();
        player.update();
        for (TriggerArea triggerArea : triggerAreas) {
            triggerArea.triggerCheck(player);
        }
        gamePad.clearTypedKeys();
        mousePad.resetClickedButtons();
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
        for (ShopStation buyStation : shopStations) {
            buyStation.draw(buffer);
        }
        for (TriggerArea triggerArea : triggerAreas) {
            triggerArea.draw(buffer);
        }
        player.draw(buffer);
    }

    private void UIDraw(Buffer buffer) {
        menuSystem.draw(buffer);
        if (GameSettings.DEBUG_MODE) {
            buffer.drawGameDebugStats();
            buffer.drawText("('D' to deactivate debug mode)", RenderingEngine.WIDTH - 200, 20, new Color(255, 255, 255));
        } else {
            buffer.drawText("('D' to activate debug mode)", RenderingEngine.WIDTH - 184, 20, new Color(255, 255, 255));
        }
    }

    private void resetStateData() {
        if (!alreadyInDisplay) {
            currentId = displayType.getId();
            gamePad.clearTypedKeys();
            mousePad.resetClickedButtons();
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
            for (ShopStation buyStation : shopStations) {
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

    private void initializeButtonSystem() {
        menuSystem = new MenuSystem();
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.inventoryButton(10, RenderingEngine.HEIGHT - 60));
        menuSystem.addButton(ButtonFactory.moneyCheatButton(RenderingEngine.WIDTH - 60, 50));
    }
}
