package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.enemies.Enemy;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.plants.*;
import cegepst.game.entities.zombies.Round;
import cegepst.game.entities.zombies.Zombie;
import cegepst.game.entities.zombies.Zombies;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.*;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.helpers.Initializer;
import cegepst.game.map.*;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplay extends Display
        implements CellListener, SlotListener,
        SunListener, RoundListener {

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
    private ArrayList<Zombie> zombies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<StaticEntity> killedEntities;
    private PlantSelector selectedPlant;
    private Round currentRound;
    private int sunCount;

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

        initializeLines();
        plants = new ArrayList<>();
        zombies = new ArrayList<>();
        projectiles = new ArrayList<>();
        currentRound = new Round(getSpawningCells());
        sunCount = 0;
        EventSystem.getInstance().addCellListener(this);
        EventSystem.getInstance().addSlotListener(this);
        EventSystem.getInstance().addSunListener(this);
        EventSystem.getInstance().addRoundListener(this);
    }

    @Override
    public int update() {
        resetStateData();
        if (inBattle) {
            battleUpdate();
        } else {
            shopUpdate();
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
        if (selectedPlant != null) {
            if (cell.isEmpty()) {
                Plant plant = selectedPlant.getPlant();
                cell.placeEntity(plant);
                plants.add(plant);
            }
        }
    }

    @Override
    public void onSlotSelection(Plant plant) {
        plant.teleport(PlantSelector.X, PlantSelector.Y_ROW1);
        selectedPlant = new PlantSelector(PlantSelector.X, PlantSelector.Y_ROW1, plant);
    }

    @Override
    public void onSlotDeselection(Plant plant) {
        selectedPlant = null;
    }

    @Override
    public void onSunCreation(Projectile projectile) {
        sunCount++;
        killedEntities.add(projectile);
    }

    @Override
    public void onSunUtilisation() {
        sunCount--;
        if (sunCount < 0) {
            sunCount = 0;
        }
    }

    @Override
    public void onZombieSpawn(Zombie zombie) {
        zombies.add(zombie);
    }

    private void battleUpdate() {
        player.setInBattle(true);
        applyColliderOnEnemies();
        battleMenuSystem.update();
        currentRound.update();
        zombies.forEach(Zombie::update);
        updateEnemies();
        updatePlants();
        handleProjectile();
        leftClickCheck();
        removeColliderOnEnemies();
    }

    private void shopUpdate() {
        player.setInBattle(false);
        applyColliderOnShop();
        shopMenuSystem.update();
        for (TriggerArea triggerArea : triggerAreas) {
            triggerArea.triggerCheck(player);
        }
        removeColliderOnShop();
    }

    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }

    private void updatePlants() {
        for (Plant plant : plants) {
            plant.update();
            if (plant.isCooldownOver()) {
                projectiles.add(plant.ability());
            }
        }
    }

    private void leftClickCheck() {
        if (mousePad.isLeftClicked()) {
            lineClickCheck();
        }
    }

    private void lineClickCheck() {
        for (Line line : lines) {
            line.checkIfCellClicked(mousePad.getPosition());
        }
    }

    private void applyColliderOnShop() {
        for (ShopStation shopStation : shopStations) {
            CollidableRepository.getInstance().registerEntity(shopStation);
        }
    }

    private void removeColliderOnShop() {
        for (ShopStation shopStation : shopStations) {
            CollidableRepository.getInstance().unregisterEntity(shopStation);
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
            lines.forEach(line -> line.draw(buffer));
            enemies.forEach(enemy -> enemy.draw(buffer));
            zombies.forEach(zombie -> zombie.draw(buffer));
            plants.forEach(plant -> plant.draw(buffer));
            projectiles.forEach(projectile -> projectile.draw(buffer));
            if (selectedPlant != null) {
                selectedPlant.draw(buffer);
            }
        } else {
            shopMap.draw(buffer);
            shopStations.forEach(shopStation -> shopStation.draw(buffer));
            triggerAreas.forEach(triggerArea -> triggerArea.draw(buffer));
        }
        player.draw(buffer);
    }

    private void UIDraw(Buffer buffer) {
        if (inBattle) {
            battleMenuSystem.draw(buffer);
            buffer.drawText("Sun: " + sunCount, 10, RenderingEngine.HEIGHT - 140, new Color(255, 255, 255));
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

    private void handleProjectile() {
        killedEntities = new ArrayList<>();
        for (Projectile projectile : projectiles) {
            projectile.update();
            for (Enemy enemy : enemies) {
                if (enemy.isColliding(projectile)) {
                    enemy.takeDamage(projectile.dealDamage());
                    if (enemy.isDead()) {
                        killedEntities.add(enemy);
                    }
                    killedEntities.add(projectile);
                }
            }
            for (StaticEntity entity : CollidableRepository.getInstance()) {
                if (projectile.hitBoxIntersectWith(entity)) {
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
        killedEntities.clear();
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
        gamePad.addKeyListener(() -> {
            if (gamePad.isRollTyped()) {
                for (ShopStation shopStation : shopStations) {
                    shopStation.roll();
                }
            }
        });
    }

    private void initializeLines() {
        lines = new ArrayList<>();
        lines.add(new Line(90));
        lines.add(new Line(190));
        lines.add(new Line(295));
        lines.add(new Line(400));
        lines.add(new Line(500));
    }

    public ArrayList<Cell> getSpawningCells() {
        ArrayList<Cell> spawningCells = new ArrayList<>();
        lines.forEach(line -> spawningCells.add(line.getSpawningCell()));
        return spawningCells;
    }
}
