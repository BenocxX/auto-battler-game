package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.settings.GameSettings;

import java.awt.*;

public class OptionMenuDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private MenuSystem menuSystem;

    public OptionMenuDisplay(DisplayType displayType, int parentDisplayId) {
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
        gamePad.update();
        gamePad.clearTypedKeys();
        mousePad.resetClickedButtons();
        updateAlreadyInDisplay();
        return currentId;
    }

    @Override
    public void draw(Buffer buffer) {
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
        menuSystem.addGamePadDevice(gamePad);
        menuSystem.addMousePadDevice(mousePad);
        menuSystem.addButton(ButtonFactory.soundButton(200, 200));
        menuSystem.addButton(ButtonFactory.musicButton(200, 260));
        menuSystem.addButton(ButtonFactory.debugButton(200, 320));
        menuSystem.addButton(ButtonFactory.backToMainMenuButton(200, 380));
        menuSystem.getButton(0).isSelected(true);
    }

    private void addKeyInputAction() {
        gamePad.addKeyListener(() -> {
            if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
                goToMainMenuDisplay();
            }
        });
    }
}
