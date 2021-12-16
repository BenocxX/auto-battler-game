package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.engine.resources.sounds.SoundPlayer;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.zombies.Zombie;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.*;
import cegepst.game.entities.shops.ShopStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.helpers.Initializer;
import cegepst.game.inventory.Inventory;
import cegepst.game.map.*;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplay extends Display implements RoundListener, GameListener {

    private final static Color white = new Color(255, 255, 255);

    private GamePad gamePad;
    private MousePad mousePad;
    private Player player;
    private World shopMap;
    private Initializer initializer;
    private MenuSystem shopMenuSystem;
    private ArrayList<ShopStation> shopStations;
    private ArrayList<TriggerArea> triggerAreas;

    public GameDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        player = new Player(gamePad);
        shopMap = new World(Sprite.SHOP_MAP.getImage());
        initializer = new Initializer();
        addKeyInputAction();
        shopStations = initializer.getShopStations();
        triggerAreas = initializer.getTriggerAreasForShopStations(shopStations);
        shopMenuSystem = initializer.getShopMenuSystem(mousePad, this);

        EventSystem.getInstance().addRoundListener(this);
        EventSystem.getInstance().addGameListener(this);
    }

    @Override
    public int update() {
        resetStateData();
        shopUpdate();
        player.update();
        gamePad.update();
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

    @Override
    public void onButtonClick(ButtonEventType eventType) {
        super.onButtonClick(eventType);
        if (ButtonEventType.BATTLE == eventType) {
            goToBattleDisplay();
        } else if (ButtonEventType.LEAVE_BATTLE == eventType) {
            player.teleport(100, 400);
        }
    }

    @Override
    public void onRoundFinished() {
        player.addMoney(200);
    }

    @Override
    public void onGameOver() {
        shopStations = initializer.getShopStations();
    }

    private void shopUpdate() {
        player.setInBattle(false);
        shopMenuSystem.update();
        for (TriggerArea triggerArea : triggerAreas) {
            triggerArea.triggerCheck(player);
        }
    }

    private void logicDraw(Buffer buffer) {
        shopMap.draw(buffer);
        shopStations.forEach(shopStation -> shopStation.draw(buffer));
        triggerAreas.forEach(triggerArea -> triggerArea.draw(buffer));
        player.draw(buffer);
    }

    private void UIDraw(Buffer buffer) {
        shopMenuSystem.draw(buffer);
        if (GameSettings.DEBUG_MODE) {
            buffer.drawGameDebugStats();
            buffer.drawText("('D' to deactivate debug mode)", RenderingEngine.WIDTH - 200, 20, white);
        } else {
            buffer.drawText("('D' to activate debug mode)", RenderingEngine.WIDTH - 184, 20, white);
        }
    }

    private void resetStateData() {
        if (!alreadyInDisplay) {
            currentId = displayType.getId();
            gamePad.clearTypedKeys();
            mousePad.resetClickedButtons();
        }
    }

    private void addKeyInputAction() {
        gamePad.addKeyListener(() -> {
            if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
                goToMainMenuDisplay();
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isDebugTyped()) {
                GameSettings.DEBUG_MODE = !GameSettings.DEBUG_MODE;
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isUseTyped()) {
                for (ShopStation shopStation : shopStations) {
                    if (shopStation.isSelected() &&
                            player.canBuy(ShopStation.ITEM_PRICE)) {
                        if (!Inventory.getInstance().containsPlant(shopStation.getPlant())) {
                            shopStation.buyItem();
                            player.buyItem(ShopStation.ITEM_PRICE);
                        } else {
                            shopStation.roll();
                        }
                    } else if (shopStation.isSelected()) {
                        SoundPlayer.play("./sounds/lock.wav");
                    }
                }
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isInventoryTyped()) {
                EventSystem.getInstance().onInventoryOpening(this);
                goToInventoryDisplay();
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isScreenModeTyped()) {
                RenderingEngine.getInstance().getScreen().toggleFullscreen();
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isRollTyped()) {
                if (player.canBuy(ShopStation.ROLL_PRICE)) {
                    shopStations.forEach(ShopStation::roll);
                    player.buyItem(ShopStation.ROLL_PRICE);
                }
            }
        });
    }
}
