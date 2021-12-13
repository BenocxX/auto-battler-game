package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.enemies.Enemy;
import cegepst.game.entities.plants.Peashooter;
import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.PlantSelector;
import cegepst.game.entities.plants.Projectile;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ButtonEventType;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.eventsystem.events.CellListener;
import cegepst.game.helpers.Initializer;
import cegepst.game.map.*;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameDisplay extends Display implements CellListener {

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
    private boolean inBattle = false;

    private ArrayList<Line> lines;
    private ArrayList<Plant> plants;
    private ArrayList<Projectile> projectiles;
    private PlantSelector plantSelector1;

    public GameDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        player = new Player(gamePad);
        battleMap = new World(Sprite.PVZ_MAP.getImage());
        shopMap = new World(Sprite.SHOP_MAP.getImage());
        initializer = new Initializer();
        addKeyInputAction();
        shopStations = initializer.getShopStations();
        triggerAreas = initializer.getTriggerAreasForShopStations(shopStations);
        shopMenuSystem = initializer.getShopMenuSystem(mousePad);
        battleMenuSystem = initializer.getBattleMenuSystem(mousePad);
        enemies = initializer.getEnemies();

        lines = new ArrayList<>();
        lines.add(new Line(90));
        lines.add(new Line(190));
        lines.add(new Line(295));
        lines.add(new Line(400));
        lines.add(new Line(500));
        plants = new ArrayList<>();
        projectiles = new ArrayList<>();
        plantSelector1 = new PlantSelector(20, 100, new Peashooter(20, 100));

        EventSystem.getInstance().addCellListener(this);
    }

    @Override
    public int update() {
        resetStateData();
        if (inBattle) {
            player.setInBattle(true);
            applyColliderOnEnemies();
            battleMenuSystem.update();
            for (Enemy enemy : enemies) {
                enemy.update();
            }
            for (Plant plant : plants) {
                plant.update();
                if (plant.canAttack()) {
                    projectiles.add(plant.fireProjectile());
                }
            }
            handleProjectile();
            if (mousePad.isLeftClicked()) {
                plantSelector1.isClicked(mousePad.getPosition());
                for (Line line : lines) {
                    line.checkIfCellClicked(mousePad.getPosition());
                }
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
            inBattle = true;
        } else if (ButtonEventType.LEAVE_BATTLE == eventType) {
            inBattle = false;
            player.teleport(100, 400);
            removeColliderOnEnemies();
        }
    }

    @Override
    public void onCellClick(Cell cell) {
        if (plantSelector1.isSelected()) {
            Plant plant = plantSelector1.getPlant();
            cell.placeEntity(plant);
            plants.add(plant);
        }
    }

    private void applyColliderOnEnemies() {
        for (Enemy enemy : enemies) {
            if (!enemy.isDead()) {
                CollidableRepository.getInstance().registerEntity(enemy);
            }
        }
    }

    private void removeColliderOnEnemies() {
        for (Enemy enemy : enemies) {
            CollidableRepository.getInstance().unregisterEntity(enemy);
        }
    }

    private void logicDraw(Buffer buffer) {
        if (inBattle) {
            battleMap.draw(buffer);
            for (Line line : lines) {
                line.draw(buffer);
            }
            for (Enemy enemy : enemies) {
                enemy.draw(buffer);
            }
            for (Plant plant : plants) {
                plant.draw(buffer);
            }
            for (Projectile projectile : projectiles) {
                projectile.draw(buffer);
            }
            plantSelector1.draw(buffer);
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

    private int getRandomEnemyIndex() {
        return enemies.get((new Random()).nextInt(enemies.size())).getId();
    }

    private void handleProjectile() {
        ArrayList<StaticEntity> killedEntities = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.update();
            for (Enemy enemy : enemies) {
                if (enemy.isColliding(projectile)) {
                    // TODO: projectile.dealDamage()
                    enemy.takeDamage(50);
                    if (enemy.isDead()) {
                        killedEntities.add(enemy);
                    }
                    killedEntities.add(projectile);
                }
            }
        }

        for (StaticEntity entity: killedEntities) {
            if (entity instanceof Enemy) {
                enemies.remove(entity);
            }
            if (entity instanceof Projectile) {
                projectiles.remove(entity);
            }
            CollidableRepository.getInstance().unregisterEntity(entity);
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
                for (ShopStation buyStation : shopStations) {
                    if (buyStation.isSelected() && player.canBuy()) {
                        buyStation.buyItem();
                        player.buyItem();
                    }
                }
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isInventoryTyped()) {
                goToInventoryDisplay();
            }
        });
        gamePad.addKeyListener(() -> {
            if (gamePad.isScreenModeTyped()) {
                RenderingEngine.getInstance().getScreen().toggleFullscreen();
            }
        });
    }
}
