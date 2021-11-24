package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.buttons.ButtonSystem;
import cegepst.engine.controls.MouseController;
import cegepst.game.GamePad;
import cegepst.game.entities.ButtonFactory;

import java.awt.*;

public class OptionMenuDisplay extends Display {

    private GamePad gamePad;
    private MouseController mouse;
    private ButtonSystem buttonSystem;

    public OptionMenuDisplay(DisplayType displayType) {
        super(displayType);
        gamePad = new GamePad();
        mouse = new MouseController();
        initializeButtonSystem();
    }

    @Override
    public int update() {
        resetStateData(gamePad);
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

    private void quitKeyCheck() {
        if (gamePad.isQuitTyped() || gamePad.isEscapeTyped()) {
            goToMainMenuDisplay();
        }
    }

    private void initializeButtonSystem() {
        buttonSystem = new ButtonSystem();
        buttonSystem.addGamePad(gamePad);
        buttonSystem.addMouse(mouse);
        buttonSystem.addButton(ButtonFactory.soundButton());
        buttonSystem.addButton(ButtonFactory.musicButton());
        buttonSystem.addButton(ButtonFactory.debugButton());
        buttonSystem.addButton(ButtonFactory.backButton(this));
        buttonSystem.getButton(0).isSelected(true);
    }
}
