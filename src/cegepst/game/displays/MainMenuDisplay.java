package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.engine.resources.images.SpriteHandler;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.shopPhase.ShopStation;
import cegepst.game.eventsystem.EventSystem;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.helpers.CenteringMachine;
import cegepst.game.resources.Sprite;
import cegepst.game.settings.GameSettings;

import java.awt.*;

public class MainMenuDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private MenuSystem menuSystem;
    private Image image;
    Rectangle screenRectangle;

    public MainMenuDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        screenRectangle = new Rectangle(RenderingEngine.WIDTH, RenderingEngine.HEIGHT);
        initializeButtonSystem();
        addKeyInputAction();
        image = SpriteHandler.resizeImage(Sprite.MENU.getImage(),
                Image.SCALE_SMOOTH, RenderingEngine.WIDTH, RenderingEngine.HEIGHT);
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
        buffer.drawImage(image, 0, 0);
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
        menuSystem.addButton(ButtonFactory.playButton(
                CenteringMachine.centerHorizontally(screenRectangle, 200), 100));
        menuSystem.addButton(ButtonFactory.optionButton(
                CenteringMachine.centerHorizontally(screenRectangle, 200), 160));
        menuSystem.addButton(ButtonFactory.quitButton(
                CenteringMachine.centerHorizontally(screenRectangle, 200), 220));
        menuSystem.getButton(0).isSelected(true);
    }

    private void addKeyInputAction() {
        gamePad.addKeyListener(() -> {
            if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
                quit();
            }
        });
    }
}
