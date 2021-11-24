package cegepst.game.displays;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.game.GameSettings;
import cegepst.game.resources.MusicHandler;

import java.awt.*;

public class AutoBattler extends Game {

    private int currentId = 0;
    private AutoBattlerGame autoBattlerGame;
    private AutoBattlerMenu autoBattlerMenu;
    private MusicHandler musicHandler;

    @Override
    public void initialize() {
        GameSettings.isFullscreenMode = false;
        autoBattlerGame = new AutoBattlerGame();
        autoBattlerMenu = new AutoBattlerMenu();
        musicHandler = new MusicHandler();
        musicHandler.play();
    }

    @Override
    public void update() {
        musicHandler.setVolumeBasedOnGameSettings(GameSettings.MUSIC);
        if (currentId == DisplayIds.MAIN_MENU.getId()) {
            currentId = autoBattlerMenu.update();
        } else if (currentId == DisplayIds.GAME.getId()) {
            currentId = autoBattlerGame.update();
        } else if (currentId == DisplayIds.QUIT.getId()) {
            stop();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (currentId == DisplayIds.MAIN_MENU.getId()) {
            buffer.setFontSize(Font.PLAIN, 20);
            autoBattlerMenu.draw(buffer);
        } else if (currentId == DisplayIds.GAME.getId()) {
            buffer.setFontSize(Font.PLAIN, 14);
            autoBattlerGame.draw(buffer);
        }
    }

    @Override
    public void conclude() {
        musicHandler.stop();
    }
}
