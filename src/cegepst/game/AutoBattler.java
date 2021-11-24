package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.displays.*;
import cegepst.game.resources.MusicHandler;

import java.util.ArrayList;

public class AutoBattler extends Game {

    private ArrayList<Display> displays;
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

        displays = new ArrayList<>();
        displays.add(mainMenuDisplay);
        displays.add(optionMenuDisplay);
        displays.add(gameDisplay);

        currentId = DisplayType.MAIN_MENU.getId();
        musicHandler = new MusicHandler();
        musicHandler.play();
    }

    @Override
    public void update() {
        musicHandler.setVolumeBasedOnGameSettings(GameSettings.MUSIC);
        for (Display display : displays) {
            if (currentId == display.getId()) {
                currentId = display.update();
                if (currentId == DisplayType.QUIT.getId()) {
                    stop();
                }
                break;
            }
        }
    }

    @Override
    public void draw(Buffer buffer) {
        for (Display display : displays) {
            if (currentId == display.getId()) {
                display.draw(buffer);
            }
        }
    }

    @Override
    public void conclude() {
        musicHandler.stop();
    }
}
