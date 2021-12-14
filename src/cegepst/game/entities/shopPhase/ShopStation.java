package cegepst.game.entities.shopPhase;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.resources.images.SpriteHandler;
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

public class ShopStation extends MovableEntity implements TriggerAreaListener, ItemBuyListener {

    public final static int PRICE = 60;

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
        plant.teleport(x + ((width - plant.getWidth()) / 2), y - (plant.getHeight() / 2));
        stoneImage = SpriteHandler.resizeImage(Sprite.SAP_TILE_SPRITE.getImage(), Image.SCALE_SMOOTH, width, height);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(stoneImage, x, y);
        if (isSelected) {
            buffer.drawHorizontallyCenteredText(plant.getName(), getBounds(), y - 35);
            buffer.drawText("(Use E to Buy)", RenderingEngine.WIDTH - 97, 40, Color.WHITE);
        }
        if (hasPlant) {
            plant.draw(buffer);
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
        plant.teleport(x + ((width - plant.getWidth()) / 2), y - (plant.getHeight() / 2));
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

    private void initializeSound() {
        sound = Sound.values()[RandomHandler.getInt(2, 5)];
    }

    private Plant getRandomPlant() {
        return Plants.values()[RandomHandler.getInt(Plants.values().length)].getPlant();
    }
}
