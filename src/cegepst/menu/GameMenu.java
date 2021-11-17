package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.controls.MouseController;
import cegepst.engine.triggers.TriggerRepository;
import cegepst.game.GamePad;

public class GameMenu extends Game {

    private GamePad gamePad;
    private MouseController mouse;
    private Button quitButton;
    private TriggerRepository triggerRepository;

    @Override
    public void initialize() {
        gamePad = new GamePad();
        mouse = new MouseController();
        triggerRepository = new TriggerRepository();
        quitButton = new Button(100, 100, 200, 50, "Quit");
        triggerRepository.addEntry(quitButton.generateTrigger(), quitButton);
    }

    @Override
    public void update() {
        quitCheck();
        triggerRepository.triggerTriggerables(mouse.getMouseRectangle());
        mouse.resetIsClicked();
    }

    @Override
    public void draw(Buffer buffer) {
        quitButton.draw(buffer);
    }

    @Override
    public void conclude() {

    }

    private void quitCheck() {
        if (gamePad.isQuitPressed()) {
            stop();
        }
        if (quitButton.isSelected() && mouse.isClicked()) {
            stop();
        }
    }
}
