package cegepst.game.entities.shops;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.engine.resources.sounds.SoundPlayer;
import cegepst.game.entities.plants.Plant;
import cegepst.game.entities.plants.Plants;
import cegepst.game.inventory.Inventory;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;
import cegepst.game.resources.Sound;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ItemBuyListener;
import cegepst.game.eventsystem.events.TriggerAreaListener;

import java.awt.*;
import java.util.ArrayList;

public class ShopStation extends MovableEntity implements TriggerAreaListener, ItemBuyListener {

    public final static int ITEM_PRICE = 100;
    public final static int ROLL_PRICE = 50;

    private Sound sound;
    private Image stoneImage;
    private Plant plant;
    private boolean isSelected;
    private boolean hasPlant;
    private int id;

    public ShopStation(int x, int y, int id) {
        setDimension(70, 44);
        teleport(x, y);
        initializeSound();
        EventSystem.getInstance().addTriggerAreaListener(this);
        EventSystem.getInstance().addItemBuyListener(this);

        this.id = id;
        hasPlant = true;
        plant = getRandomPlant();
        if (plant != null) {
            plant.teleport(x + ((width - plant.getWidth()) / 2), y - (plant.getHeight() / 2));
        }
        stoneImage = SpriteHandler.resizeImage(Sprite.SAP_TILE_SPRITE.getImage(), Image.SCALE_SMOOTH, width, height);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(stoneImage, x, y);
        if (hasPlant) {
            plant.draw(buffer);
            if (isSelected) {
                buffer.drawHorizontallyCenteredText(plant.getName(), getBounds(), y - 70);
                buffer.drawText("(Use E to Buy)", RenderingEngine.WIDTH - 97, 40, Color.WHITE);
            }
        }
    }

    @Override
    public void onTriggerEnter(int triggerId) {
        if (id == triggerId && hasPlant) {
            isSelected = true;
        }
    }

    @Override
    public void onTrigger(int triggerId) {
        if (id == triggerId && !hasPlant) {
            isSelected = false;
        }
    }

    @Override
    public void onTriggerLeave(int triggerId) {
        if (id == triggerId) {
            isSelected = false;
        }
    }

    @Override
    public void onItemBuy(int itemId) {
        if (id == itemId) {
            hasPlant = false;
            sound.play(GameSettings.SOUND);
        }
    }

    public void roll() {
        hasPlant = true;
        plant = getRandomPlant();
        SoundPlayer.play("./sounds/roll.wav");
        if (plant == null)  {
            hasPlant = false;
        } else {
            plant.teleport(x + ((width - plant.getWidth()) / 2), y - (plant.getHeight() / 2));
        }
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void buyItem() {
        if (hasPlant) {
            EventSystem.getInstance().onItemBuy(id);
            Inventory.getInstance().addItem(plant);
        }
    }

    public Plant getPlant() {
        return plant;
    }

    private void initializeSound() {
        sound = Sound.values()[RandomHandler.getInt(2, 5)];
    }

    private Plant getRandomPlant() {
        Plants[] plantTypes = Plants.values();
        ArrayList<Plant> plants = new ArrayList<>();
        for (Plants plantType: plantTypes) {
            plants.add(plantType.getPlant());
        }

        ArrayList<Plant> notAlreadyPossessedPlants = new ArrayList<>();
        for (Plant plant : plants) {
            if (!Inventory.getInstance().containsPlant(plant)) {
                notAlreadyPossessedPlants.add(plant);
            }
        }

        if (notAlreadyPossessedPlants.isEmpty()) {
            return null;
        } else {
            return notAlreadyPossessedPlants.get(RandomHandler.getInt(notAlreadyPossessedPlants.size()));
        }
    }
}
