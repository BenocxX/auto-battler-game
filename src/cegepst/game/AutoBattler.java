package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.displays.*;
import cegepst.game.resources.MusicHandler;

import java.util.ArrayList;

// TODO: Rename to DisplaySelector
public class AutoBattler extends Game {

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

    private void initializeDisplays() {
        displays = new ArrayList<>();
        displays.add(new MainMenuDisplay(DisplayType.MAIN_MENU));
        displays.add(new OptionMenuDisplay(DisplayType.OPTION_MENU));
        displays.add(new GameDisplay(DisplayType.GAME));
    }
}
