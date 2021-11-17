package cegepst;

import cegepst.game.AutoBattlerGame;
import cegepst.menu.Menu;

public class App {

    public static void main(String[] args) {
        (new Menu()).start();
        (new AutoBattlerGame()).start();
    }
}
