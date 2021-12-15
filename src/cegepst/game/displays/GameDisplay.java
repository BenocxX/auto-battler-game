package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.enemies.Enemy;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.plants.*;
import cegepst.game.entities.zombies.Round;
import cegepst.game.entities.zombies.Rounds;
import cegepst.game.entities.zombies.Zombie;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.*;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.entities.Player;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.helpers.Initializer;
import cegepst.game.helpers.RoundFactory;
import cegepst.game.inventory.Inventory;
import cegepst.game.map.*;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;

public class GameDisplay extends Display
        implements CellListener, SlotListener,
        SunListener, RoundListener, PlantListener {

    private final static Color white = new Color(255, 255, 255);

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
    private ArrayList<Line> lines;
    private ArrayList<Plant> plants;
    private ArrayList<Zombie> zombies;
    private ArrayList<Projectile> projectiles;
    private ArrayList<StaticEntity> killedEntities;
    private ArrayList<Plant> killedPlants;
    private PlantSelector selectedPlant;
    private TriggerArea houseTriggerArea;
    private Rounds[] rounds;
    private Round currentRound;
    private int roundCount;
    private boolean inBattle = false;
    private boolean isGameOver = false;
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

        initializeLines();
        plants = new ArrayList<>();
        zombies = new ArrayList<>();
        projectiles = new ArrayList<>();
        killedPlants = new ArrayList<>();
        houseTriggerArea = new TriggerArea(0, 0, 175, RenderingEngine.HEIGHT);
        rounds = Rounds.values();
        roundCount = 0;
        currentRound = RoundFactory.getRound(rounds[roundCount].getNbZombies());
        sunCount = 200;
        EventSystem.getInstance().addCellListener(this);
        EventSystem.getInstance().addSlotListener(this);
        EventSystem.getInstance().addSunListener(this);
        EventSystem.getInstance().addRoundListener(this);
        EventSystem.getInstance().addPlantListener(this);
    }

    @Override
    public int update() {
        if (isGameOver) {
            resetGame();
            isGameOver = false;
        }
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
        }
    }

    @Override
    public void onCellClick(Cell cell) {
        if (selectedPlant != null) {
            if (cell.isEmpty()) {
                Plant plant = selectedPlant.getPlant();
                if (sunCount >= plant.getSunPrice()) {
                    sunCount -= plant.getSunPrice();
                    cell.placeEntity(plant);
                    plants.add(plant);
                }
            }
        }
    }

    @Override
    public void onSlotSelection(Plant plant) {
        selectedPlant = new PlantSelector(plant);
    }

    @Override
    public void onSlotDeselection(Plant plant) {
        selectedPlant = null;
    }

    @Override
    public void onSunCreation(Projectile projectile) {
        sunCount += 100;
        killedEntities.add(projectile);
    }

    @Override
    public void onZombieSpawn(Zombie zombie) {
        ArrayList<Cell> spawningCells = getSpawningCells();
        spawningCells.get(RandomHandler.getInt(spawningCells.size())).placeEntity(zombie);
        zombies.add(zombie);
    }

    @Override
    public void onRoundFinished() {
        if (roundCount < rounds.length - 1) {
            roundCount++;
            currentRound = RoundFactory.getRound(rounds[roundCount].getNbZombies());
        }
        player.addMoney(100 * roundCount);
    }

    @Override
    public void onPlantDeath(Plant plant) {
        plants.forEach(plant1 -> {
            if (plant1 == plant) {
                killedPlants.add(plant);
            }
        });
    }

    private void battleUpdate() {
        player.setInBattle(true);
        battleMenuSystem.update();
        currentRound.update();
        zombies.forEach(Zombie::update);
        zombies.forEach(zombie -> {
            if (houseTriggerArea.intersectWith(zombie)) {
                isGameOver = true;
            }
            plants.forEach(plant -> {
                if (zombie.isColliding(plant)) {
                    zombie.setEating(true, plant);
                }
            });
        });
        updatePlants();
        handleProjectile();
        leftClickCheck();
    }

    private void resetGame() {
        inBattle = false;
        lines.forEach(Line::emptyCells);
        shopStations = initializer.getShopStations();
        plants.clear();
        selectedPlant = null;
        projectiles.clear();
        zombies.clear();
        roundCount = 0;
        Inventory.getInstance().clear();
        RoundFactory.getRound(rounds[roundCount].getNbZombies());
        sunCount = 200;
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

    private void logicDraw(Buffer buffer) {
        if (inBattle) {
            battleMap.draw(buffer);
            houseTriggerArea.draw(buffer);
            lines.forEach(line -> line.draw(buffer));
            zombies.forEach(zombie -> zombie.draw(buffer));
            plants.forEach(plant -> plant.draw(buffer));
            projectiles.forEach(projectile -> projectile.draw(buffer));
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
            drawGameInfoUI(buffer);
        } else {
            shopMenuSystem.draw(buffer);
        }
        if (GameSettings.DEBUG_MODE) {
            buffer.drawGameDebugStats();
            buffer.drawText("('D' to deactivate debug mode)", RenderingEngine.WIDTH - 200, 20, white);
        } else {
            buffer.drawText("('D' to activate debug mode)", RenderingEngine.WIDTH - 184, 20, white);
        }
    }

    private void drawGameInfoUI(Buffer buffer) {
        int padding = 10;
        Rectangle uiInfoBox = new Rectangle(10, 50, 125, 200);
        buffer.drawRoundRectangle(uiInfoBox.x, uiInfoBox.y,
                uiInfoBox.width, uiInfoBox.height, 20,
                20, new Color(0, 0, 0, 150));
        if (selectedPlant != null) {
            buffer.drawText("Selected plant:", uiInfoBox.x + padding, selectedPlant.getY() - 30, white);
            selectedPlant.draw(buffer);
        }
        buffer.drawText("Round #" + (roundCount + 1), uiInfoBox.x + padding, uiInfoBox.y + uiInfoBox.height - 30, white);
        buffer.drawText("Sun: " + sunCount, uiInfoBox.x + padding, uiInfoBox.y + uiInfoBox.height - padding, white);
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
            zombies.forEach(zombie -> {
                if (zombie.isColliding(projectile)) {
                    zombie.takeDamage(projectile.dealDamage());
                    if (zombie.isDead()) {
                        killedEntities.add(zombie);
                    }
                    killedEntities.add(projectile);
                }
            });
            for (StaticEntity entity : CollidableRepository.getInstance()) {
                if (projectile.hitBoxIntersectWith(entity)) {
                    killedEntities.add(projectile);
                }
            }
        }

        killedEntities.addAll(killedPlants);
        killedPlants.clear();

        for (StaticEntity entity: killedEntities) {
            if (entity instanceof Zombie) {
                zombies.remove(entity);
            }
            if (entity instanceof Projectile) {
                projectiles.remove(entity);
            }
            if (entity instanceof Plant) {
                plants.remove(entity);
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
                    if (buyStation.isSelected() && player.canBuy(ShopStation.ITEM_PRICE)) {
                        buyStation.buyItem();
                        player.buyItem(ShopStation.ITEM_PRICE);
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
                if (player.canBuy(ShopStation.ROLL_PRICE)) {
                    shopStations.forEach(ShopStation::roll);
                    player.buyItem(ShopStation.ROLL_PRICE);
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
