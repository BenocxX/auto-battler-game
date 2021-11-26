package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.ButtonSystem;
import cegepst.engine.controls.MousePad;
import cegepst.game.GamePad;
import cegepst.game.entities.ButtonFactory;

import java.awt.*;

public class MainMenuDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private ButtonSystem buttonSystem;

    public MainMenuDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mousePad = new MousePad();
        initializeButtonSystem();
    }

    @Override
    public int update() {
        resetStateData();
        quitKeyCheck();
        buttonSystem.update();
        gamePad.clearTypedKeys();
        updateAlreadyInDisplay();
        return currentId;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.setFontSize(Font.PLAIN, 20);
        buttonSystem.draw(buffer);
    }

    private void resetStateData() {
        if (!alreadyInDisplay) {
            currentId = displayType.getId();
            gamePad.clearTypedKeys();
            mousePad.resetIsClicked();
        }
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            quit();
        }
    }

    private void initializeButtonSystem() {
        buttonSystem = new ButtonSystem();
        buttonSystem.addGamePadDevice(gamePad);
        buttonSystem.addMouseDevice(mousePad);
        buttonSystem.addButton(ButtonFactory.playButton(this));
        buttonSystem.addButton(ButtonFactory.optionButton(this));
        buttonSystem.addButton(ButtonFactory.quitButton(this));
        buttonSystem.getButton(0).isSelected(true);
    }
}