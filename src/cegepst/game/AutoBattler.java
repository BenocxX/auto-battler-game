package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.displays.DisplayType;
import cegepst.game.displays.GameDisplay;
import cegepst.game.displays.MainMenuDisplay;
import cegepst.game.displays.OptionMenuDisplay;
import cegepst.game.resources.MusicHandler;

import java.awt.*;

public class AutoBattler extends Game {

    private MusicHandler musicHandler;
    private MainMenuDisplay mainMenuDisplay;
    private OptionMenuDisplay optionMenuDisplay;
    private GameDisplay gameDisplay;
    private int currentId;

    @Override
    public void initialize() {
        GameSettings.isFullscreenMode = false;
        mainMenuDisplay = new MainMenuDisplay(DisplayType.MAIN_MENU);
        optionMenuDisplay = new OptionMenuDisplay(DisplayType.OPTION_MENU);
        gameDisplay = new GameDisplay(DisplayType.GAME);
        currentId = DisplayType.MAIN_MENU.getId();
        musicHandler = new MusicHandler();
        musicHandler.play();
    }

    @Override
    public void update() {
        musicHandler.setVolumeBasedOnGameSettings(GameSettings.MUSIC);
        if (currentId == mainMenuDisplay.getId()) {
            currentId = mainMenuDisplay.update();
        } else if (currentId == optionMenuDisplay.getId()) {
            currentId = optionMenuDisplay.update();
        } else if (currentId == gameDisplay.getId()) {
            currentId = gameDisplay.update();
        } else {
            stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (currentId == mainMenuDisplay.getId()) {
            mainMenuDisplay.draw(buffer);
        } else if (currentId == optionMenuDisplay.getId()) {
            optionMenuDisplay.draw(buffer);
        } else if (currentId == gameDisplay.getId()) {
            gameDisplay.draw(buffer);
        }
    }

    @Override
    public void conclude() {
        musicHandler.stop();
    }
}
