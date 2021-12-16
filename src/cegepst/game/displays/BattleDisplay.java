package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.menu.MenuSystem;
import cegepst.engine.resources.sounds.SoundPlayer;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.miscellaneous.TriggerArea;
import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.PlantSelector;
import cegepst.game.entities.projectiles.Projectile;
import cegepst.game.entities.zombies.Round;
import cegepst.game.entities.zombies.Rounds;
import cegepst.game.entities.zombies.Zombie;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.*;
import cegepst.game.helpers.Initializer;
import cegepst.game.helpers.RoundFactory;
import cegepst.game.inventory.Inventory;
import cegepst.game.map.Cell;
import cegepst.game.map.Line;
import cegepst.game.map.World;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;
import java.util.ArrayList;

public class BattleDisplay extends Display
        implements CellListener, SlotListener,
        SunListener, ZombieListener, RoundListener,
        PlantListener, GameListener {

    private final static Color white = new Color(255, 255, 255);

    private Initializer initializer;
    private GamePad gamePad;
    private MousePad mousePad;
    private MenuSystem menuSystem;
    private World battleMap;
    private TriggerArea houseTriggerArea;
    private PlantSelector selectedPlant;
    private ArrayList<Projectile> projectiles;
    private ArrayList<Plant> plants;
    private ArrayList<Plant> killedPlants;
    private ArrayList<StaticEntity> killedEntities;
    private ArrayList<Zombie> zombies;
    private ArrayList<Line> lines;
    private Rounds[] rounds;
    private Round currentRound;
    private int roundCount;
    private boolean isGameOver = false;
    private int sunCount;

    public BattleDisplay(DisplayType displayType) {
        super(displayType);
        initializer = new Initializer();
        gamePad = new GamePad();
        mousePad = new MousePad();
        menuSystem = initializer.getBattleMenuSystem(mousePad, this);
        battleMap = new World(Sprite.PVZ_MAP.getImage());
        addKeyInputAction();
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
        EventSystem.getInstance().addZombieListener(this);
        EventSystem.getInstance().addRoundListener(this);
        EventSystem.getInstance().addPlantListener(this);
        EventSystem.getInstance().addGameListener(this);
    }

    @Override
    public int update() {
        if (isGameOver) {
            EventSystem.getInstance().onGameOver();
        }
        battleUpdate();
        resetStateData();
        gamePad.update();
        gamePad.clearTypedKeys();
        mousePad.resetClickedButtons();
        updateAlreadyInDisplay();
        return currentId;
    }

    @Override
    public void draw(Buffer buffer) {
        battleMap.draw(buffer);
        houseTriggerArea.draw(buffer);
        lines.forEach(line -> line.draw(buffer));
        zombies.forEach(zombie -> zombie.draw(buffer));
        plants.forEach(plant -> plant.draw(buffer));
        projectiles.forEach(projectile -> projectile.draw(buffer));
        menuSystem.draw(buffer);
        drawGameInfoUI(buffer);
    }

    @Override
    public void onButtonClick(ButtonEventType eventType) {
        super.onButtonClick(eventType);
        if (ButtonEventType.LEAVE_BATTLE == eventType) {
            goToGameDisplay();
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
                    cell.addPlant(plant);
                    plants.add(plant);
                    switch (RandomHandler.getInt(3)) {
                        case 0 -> SoundPlayer.play("./sounds/dirt1.wav");
                        case 1 -> SoundPlayer.play("./sounds/dirt2.wav");
                        case 2 -> SoundPlayer.play("./sounds/dirt3.wav");
                    }
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
    }

    @Override
    public void onPlantDeath(Plant plant) {
        plants.forEach(plant1 -> {
            if (plant1 == plant) {
                killedPlants.add(plant);
            }
        });
    }

    @Override
    public void onGameOver() {
        lines.forEach(Line::emptyCells);
        plants.clear();
        selectedPlant = null;
        projectiles.clear();
        zombies.clear();
        Inventory.getInstance().clear();
        roundCount = 0;
        currentRound = RoundFactory.getRound(rounds[roundCount].getNbZombies());
        sunCount = 200;
        isGameOver = false;
        goToGameOverDisplay();
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

    private void battleUpdate() {
        menuSystem.update();
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
