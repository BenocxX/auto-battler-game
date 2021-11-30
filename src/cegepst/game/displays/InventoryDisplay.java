package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.inventory.Inventory;

public class InventoryDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private MenuSystem menuSystem;

    public InventoryDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        initializeButtonSystem();
    }

    @Override
    public int update() {
        resetStateData();
        quitKeyCheck();
        menuSystem.update();
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

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            goToGameDisplay();
        }
    }

    private void initializeButtonSystem() {
        menuSystem = new MenuSystem();
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.backToGameButton(10, RenderingEngine.HEIGHT - 60));
        menuSystem.getButton(0).isSelected(true);
    }
}