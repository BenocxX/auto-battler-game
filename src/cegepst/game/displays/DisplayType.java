package cegepst.game.displays;

public enum DisplayType {

    QUIT(-1),
    MAIN_MENU(0),
    OPTION_MENU(1),
    GAME(2);

    private int id;

    DisplayType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
