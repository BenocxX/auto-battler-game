package cegepst.game;

import cegepst.engine.Buffer;
import cegepst.engine.Game;

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
            autoBattlerMenu.draw(buffer);
        } else {
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
