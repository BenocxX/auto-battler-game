package cegepst;

import cegepst.game.AutoBattlerGame;
import cegepst.menu.GameMenu;

public class App {

    private static GameMenu menu;
    private static AutoBattlerGame game;

    public static void main(String[] args) {
        menu = new GameMenu();
        game = new AutoBattlerGame();
        while (true) {
            menu.start();
            game.start();
        }
    }
}
