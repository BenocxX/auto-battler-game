package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.helpers.LoopingIndex;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.enemies.Enemy;
import cegepst.game.entities.enemies.RunnerZombie;
import cegepst.game.entities.enemies.Zombie;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.helpers.CenteringMachine;
import cegepst.game.helpers.Initializer;
import cegepst.game.map.DefendingRow;
import cegepst.game.map.AttackingRow;
import cegepst.game.map.Row;
import cegepst.game.map.World;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private Player player;
    private World shopMap;
    private World battleMap;
    private Initializer initializer;
    private MenuSystem shopMenuSystem;
    private MenuSystem battleMenuSystem;
    private ArrayList<ShopStation> shopStations;
    private ArrayList<TriggerArea> triggerAreas;
    private ArrayList<Enemy> enemies;
    private ArrayList<Row> rows;
    private ArrayList<AttackingRow> attackingRows;
    private ArrayList<DefendingRow> defendingRows;
    private LoopingIndex currentRowLoopingIndex;
    private boolean inBattle = false;

    public GameDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        player = new Player(gamePad);
        battleMap = new World(Sprite.BATTLE_MAP.getImage());
        shopMap = new World(Sprite.SHOP_MAP.getImage());
        initializer = new Initializer();
        shopStations = initializer.getShopStations();
        triggerAreas = initializer.getTriggerAreasForShopStations(shopStations);
        initializeButtonSystem();
        enemies = initializer.getEnemies();
        attackingRows = initializer.getAttackingRows();
        defendingRows = initializer.getDefendingRows(attackingRows);
        rows = initializer.initializeRows(attackingRows, defendingRows);
        currentRowLoopingIndex = new LoopingIndex(1, 0, defendingRows.size() -1);
    }

    @Override
    public int update() {
        resetStateData();
        keysInputCheck();
        if (inBattle) {
            player.setInBattle(true);
            applyColliderOnEnemies();
            battleMenuSystem.update();
            for (Enemy enemy : enemies) {
                CollidableRepository.getInstance().registerEntity(enemy);
                enemy.update();
                enemy.checkIfTouchingPlayer(player);
            }
            removeColliderOnEnemies();
        } else {
            player.setInBattle(false);
            shopMenuSystem.update();
            for (TriggerArea triggerArea : triggerAreas) {
                triggerArea.triggerCheck(player);
            }
        }
        player.update();
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
            inBattle = true;
            defendingRows.get(currentRowLoopingIndex.getIndex()).movePlayer(player);
        } else if (ButtonEventType.LEAVE_BATTLE == eventType) {
            inBattle = false;
            player.teleport(100, 400);
            removeColliderOnEnemies();
        }
    }

    private void applyColliderOnEnemies() {
        for (StaticEntity enemy : enemies) {
            CollidableRepository.getInstance().registerEntity(enemy);
        }
    }

    private void removeColliderOnEnemies() {
        for (StaticEntity enemy : enemies) {
            CollidableRepository.getInstance().unregisterEntity(enemy);
        }
    }

    private void logicDraw(Buffer buffer) {
        if (inBattle) {
            battleMap.draw(buffer);
            for (Row row : rows) {
                row.draw(buffer);
            }
            for (Enemy enemy : enemies) {
                enemy.draw(buffer);
            }
        } else {
            shopMap.draw(buffer);
            for (ShopStation buyStation : shopStations) {
                buyStation.draw(buffer);
            }
            for (TriggerArea triggerArea : triggerAreas) {
                triggerArea.draw(buffer);
            }
        }
        player.draw(buffer);
    }

    private void UIDraw(Buffer buffer) {
        if (inBattle) {
            battleMenuSystem.draw(buffer);
        } else {
            shopMenuSystem.draw(buffer);
        }
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
        attackKeyCheck();
        moveRowKeyCheck();
        useKeyCheck();
        inventoryKeyCheck();
        screenModeKeyCheck();
    }

    private void inventoryKeyCheck() {
        if (gamePad.isInventoryTyped()) {
            goToInventoryDisplay();
        }
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

    private void attackKeyCheck() {
        if (gamePad.isAttackTyped()) {
            EventSystem.getInstance().onRowAttack(
                    attackingRows.get(0).getEnemies(enemies), player.dealDamage());
        }
    }

    private void moveRowKeyCheck() {
        if (inBattle) {
            if (gamePad.isMoveRowUpTyped()) {
                currentRowLoopingIndex.decrement();
                defendingRows.get(currentRowLoopingIndex.getIndex()).movePlayer(player);
            }
            if (gamePad.isMoveRowDownTyped()) {
                currentRowLoopingIndex.increment();
                defendingRows.get(currentRowLoopingIndex.getIndex()).movePlayer(player);
            }
        }
    }

    private int getRandomEnemyIndex() {
        return enemies.get((new Random()).nextInt(enemies.size())).getId();
    }

    private void useKeyCheck() {
        if (gamePad.isUseTyped()) {
            for (ShopStation buyStation : shopStations) {
                if (buyStation.isSelected() && player.canBuy()) {
                    buyStation.buyItem();
                    player.buyItem();
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
        shopMenuSystem = new MenuSystem();
        shopMenuSystem.addMousePadDevice(mousePad);
        shopMenuSystem.addButton(ButtonFactory.inventoryButton(10, RenderingEngine.HEIGHT - 60));
        shopMenuSystem.addButton(ButtonFactory.moneyCheatButton(220, RenderingEngine.HEIGHT - 60));
        shopMenuSystem.addButton(ButtonFactory.battleButton(10, RenderingEngine.HEIGHT - 120));

        battleMenuSystem = new MenuSystem();
        battleMenuSystem.addMousePadDevice(mousePad);
        battleMenuSystem.addButton(ButtonFactory.inventoryButton(10, RenderingEngine.HEIGHT - 60));
        battleMenuSystem.addButton(ButtonFactory.leaveBattleButton(10, RenderingEngine.HEIGHT - 120));
    }
}
