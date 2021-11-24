package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.GameSettings;
import cegepst.game.displays.DisplayType;
import cegepst.game.displays.GameDisplay;
import cegepst.game.displays.MainMenuDisplay;
import cegepst.game.displays.OptionMenuDisplay;
import cegepst.game.resources.MusicHandler;

import java.awt.*;

public class AutoBattler extends Game {

    private int currentId = 0;
    private GameDisplay gameDisplay;
    private MainMenuDisplay mainMenuDisplay;
    private OptionMenuDisplay optionMenuDisplay;
    private MusicHandler musicHandler;

    @Override
    public void initialize() {
        GameSettings.isFullscreenMode = false;
        mainMenuDisplay = new MainMenuDisplay(DisplayType.MAIN_MENU);
        optionMenuDisplay = new OptionMenuDisplay(DisplayType.OPTION_MENU);
        gameDisplay = new GameDisplay(DisplayType.GAME);
        musicHandler = new MusicHandler();
        musicHandler.play();
    }

    @Override
    public void update() {
        musicHandler.setVolumeBasedOnGameSettings(GameSettings.MUSIC);
        if (currentId == DisplayType.MAIN_MENU.getId()) {
            currentId = mainMenuDisplay.update();
        } else if (currentId == DisplayType.GAME.getId()) {
            currentId = gameDisplay.update();
        } else if (currentId == DisplayType.OPTION_MENU.getId()) {
            currentId = optionMenuDisplay.update();
        } else if (currentId == DisplayType.QUIT.getId()) {
            stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (currentId == DisplayType.MAIN_MENU.getId()) {
            buffer.setFontSize(Font.PLAIN, 20);
            mainMenuDisplay.draw(buffer);
        } else if (currentId == DisplayType.OPTION_MENU.getId()) {
            optionMenuDisplay.draw(buffer);
        } else if (currentId == DisplayType.GAME.getId()) {
            buffer.setFontSize(Font.PLAIN, 14);
            gameDisplay.draw(buffer);
        }
    }

    @Override
    public void conclude() {
        musicHandler.stop();
    }
}
