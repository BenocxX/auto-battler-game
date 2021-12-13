package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.inventory.Inventory;
import cegepst.game.settings.GameSettings;

public class InventoryDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private MenuSystem menuSystem;

    public InventoryDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        initializeButtonSystem();
        addKeyInputAction();
    }

    @Override
    public int update() {
        resetStateData();
        menuSystem.update();
        Inventory.getInstance().update();
        gamePad.update();
        gamePad.clearTypedKeys();
        mousePad.resetClickedButtons();
        updateAlreadyInDisplay();
        return currentId;
    }

    @Override
    public void draw(Buffer buffer) {
        Inventory.getInstance().draw(buffer);
        menuSystem.draw(buffer);
    }

    private void resetStateData() {
        if (!alreadyInDisplay) {
            currentId = displayType.getId();
            gamePad.clearTypedKeys();
            mousePad.resetClickedButtons();
        }
    }

    private void initializeButtonSystem() {
        menuSystem = new MenuSystem();
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.backToGameButton(10, RenderingEngine.HEIGHT - 60));
        menuSystem.getButton(0).isSelected(true);
    }

    private void addKeyInputAction() {
        gamePad.addKeyListener(() -> {
            if (gamePad.isQuitTyped() || gamePad.isEscapeTyped() || gamePad.isInventoryTyped()) {
                goToGameDisplay();
            }
        });
    }
}
