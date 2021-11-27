package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.ButtonSystem;
import cegepst.engine.controls.MouseController;
import cegepst.game.controls.GamePad;
import cegepst.game.controls.MousePad;
import cegepst.game.entities.ButtonFactory;

import java.awt.*;

public class OptionMenuDisplay extends Display {

    private GamePad gamePad;
    private MousePad mousePad;
    private ButtonSystem buttonSystem;

    public OptionMenuDisplay(DisplayType displayType) {
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
        mousePad.resetClickedButtons();
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
            mousePad.resetClickedButtons();
        }
    }

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            goToMainMenuDisplay();
        }
    }

    private void initializeButtonSystem() {
        buttonSystem = new ButtonSystem();
        buttonSystem.addGamePadDevice(gamePad);
        buttonSystem.addMouseDevice(mousePad);
        buttonSystem.addButton(ButtonFactory.soundButton());
        buttonSystem.addButton(ButtonFactory.musicButton());
        buttonSystem.addButton(ButtonFactory.debugButton());
        buttonSystem.addButton(ButtonFactory.backButton(this));
        buttonSystem.getButton(0).isSelected(true);
    }
}
