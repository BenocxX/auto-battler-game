package cegepst.game.entities.shopPhase;

import cegepst.engine.Buffer;
import cegepst.engine.CollidableRepository;
import cegepst.engine.RenderingEngine;
import cegepst.engine.entities.MovableEntity;
import cegepst.engine.helpers.RandomHandler;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.settings.GameSettings;
import cegepst.game.resources.Sound;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.eventsystem.events.ItemBuyListener;
import cegepst.game.eventsystem.events.TriggerAreaListener;
import cegepst.game.resources.Sprite;

import java.awt.*;

public class ShopStation extends MovableEntity implements TriggerAreaListener, ItemBuyListener {

    private Sound sound;
    private Image image;
    private Item item;
    private boolean isSelected;
    private boolean hasItem;
    private int id;

    public ShopStation(int x, int y, int id) {
        setDimension(70, 44);
        teleport(x, y);
        initializeSound();
        CollidableRepository.getInstance().registerEntity(this);
        EventSystem.getInstance().addTriggerAreaListener(this);
        EventSystem.getInstance().addItemBuyListener(this);


        this.id = id;
        hasItem = true;
        item = new CreatureItem(id, x + ((width - CreatureItem.WIDTH) / 2), y - (CreatureItem.HEIGHT / 2));
        image = SpriteHandler.resizeImage(Sprite.SAP_TILE_SPRITE.getImage(), Image.SCALE_SMOOTH, width, height);
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawImage(image, x, y);
        if (isSelected) {
            buffer.drawHorizontallyCenteredText(item.getName(), getBounds(), y - 35);
            buffer.drawText("(Use E to Buy)", RenderingEngine.WIDTH - 97, 40, Color.WHITE);
        }
        item.draw(buffer);
    }

    @Override
    public void onTriggerEnter(int triggerId) {
        if (id == triggerId && hasItem) {
            isSelected = true;
        }
    }

    @Override
    public void onTrigger(int triggerId) {
        if (id == triggerId && !hasItem) {
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
            hasItem = false;
            sound.play(GameSettings.SOUND);
        }
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void buyItem() {
        if (hasItem) {
            item.buy();
        }
    }

    private void initializeSound() {
        sound = Sound.values()[RandomHandler.getInt(2, 5)];
    }
}
