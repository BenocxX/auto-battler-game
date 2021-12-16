package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.displays.*;
import cegepst.game.resources.MusicHandler;
import cegepst.game.settings.GameSettings;

import java.util.ArrayList;

public class DisplaySelector extends Game {

    private ArrayList<Display> displays;
    private MusicHandler musicHandler;
    private int currentId;

    @Override
    public void initialize() {
        GameSettings.isFullscreenMode = false;
        initializeDisplays();
        currentId = DisplayType.MAIN_MENU.getId();
        musicHandler = new MusicHandler();
        musicHandler.play();
    }

    @Override
    public void update() {
        musicHandler.setVolumeBasedOnGameSettings(GameSettings.MUSIC);
        updateDisplay();
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

    private void updateDisplay() {
        for (Display display : displays) {
            if (currentId == display.getId()) {
                currentId = display.update();
                quitCheck();
                break;
            }
        }
    }

    private void quitCheck() {
        if (currentId == DisplayType.QUIT.getId()) {
            stop();
        }
    }

    private void initializeDisplays() {
        displays = new ArrayList<>();
        displays.add(new MainMenuDisplay(DisplayType.MAIN_MENU));
        displays.add(new OptionMenuDisplay(DisplayType.OPTION_MENU, DisplayType.MAIN_MENU.getId()));
        displays.add(new GameDisplay(DisplayType.GAME));
        displays.add(new InventoryDisplay(DisplayType.INVENTORY));
        displays.add(new GameOverDisplay(DisplayType.GAME_OVER));
        displays.add(new BattleDisplay(DisplayType.BATTLE));
    }
}
