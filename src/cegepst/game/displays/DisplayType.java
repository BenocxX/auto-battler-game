package cegepst.game.displays;

public enum DisplayType {

    QUIT(-1),
    MAIN_MENU(0),
    OPTION_MENU(1),
    SHOP(2),
    INVENTORY(3),
    GAME_OVER(4),
    BATTLE(5);

    private int id;

    DisplayType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
