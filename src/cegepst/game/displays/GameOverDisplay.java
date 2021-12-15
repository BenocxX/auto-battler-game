package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.RenderingEngine;
import cegepst.engine.menu.MenuSystem;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.helpers.ButtonFactory;
import cegepst.game.helpers.CenteringMachine;

import java.awt.*;

public class GameOverDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private MenuSystem menuSystem;

    public GameOverDisplay(DisplayType displayType) {
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
        buffer.drawHorizontallyCenteredText("Game Over", new Rectangle(RenderingEngine.WIDTH,
                RenderingEngine.HEIGHT), 80);
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
        menuSystem.addButton(ButtonFactory.backToGame(
                CenteringMachine.centerHorizontally(new Rectangle(RenderingEngine.WIDTH,
                        RenderingEngine.HEIGHT), 200), 100));
        menuSystem.addButton(ButtonFactory.backToMainMenu(
                CenteringMachine.centerHorizontally(new Rectangle(RenderingEngine.WIDTH,
                        RenderingEngine.HEIGHT), 200), 160));
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
