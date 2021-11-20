package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

import java.awt.*;

public class AutoBattler extends Game {

    private AutoBattlerGame autoBattlerGame;
    private AutoBattlerMenu autoBattlerMenu;
    private boolean inMenu = true;

    @Override
    public void initialize() {
        autoBattlerGame = new AutoBattlerGame();
        autoBattlerMenu = new AutoBattlerMenu();
    }

    @Override
    public void update() {
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

    }

    private void stopCheck() {
        if (autoBattlerMenu.isQuitting()) {
            stop();
        }
    }
}
