package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;
import cegepst.engine.resources.sounds.ClipHandler;
import cegepst.game.resources.MusicHandler;
import cegepst.game.resources.Sound;

import java.awt.*;

public class AutoBattler extends Game {

    private boolean inMenu = true;
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
        if (inMenu) {
            inMenu = autoBattlerMenu.update();
            stopCheck();
            autoBattlerGame.resetStateData();
        } else {
            inMenu = !autoBattlerGame.update();
            autoBattlerMenu.resetStateData();
        }
    }

    @Override
    public void draw(Buffer buffer) {
        if (inMenu) {
            buffer.setFontSize(Font.PLAIN, 20);
            autoBattlerMenu.draw(buffer);
        } else {
            buffer.setFontSize(Font.PLAIN, 14);
            autoBattlerGame.draw(buffer);
        }
    }

    @Override
    public void conclude() {
        musicHandler.stop();
    }

    private void stopCheck() {
        if (autoBattlerMenu.isQuitting()) {
            stop();
        }
    }
}
