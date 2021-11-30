package cegepst.game.resources;

import cegepst.engine.resources.sounds.ClipHandler;
import cegepst.engine.resources.sounds.SoundPlayer;

public enum Sound {

    MUSIC("./musics/map1.wav"),
    ENEMY_1("./sounds/best1.wav"),
    BUY_1("./sounds/buy1.wav"),
    BUY_2("./sounds/buy2.wav"),
    BUY_3("./sounds/buy3.wav"),
    BUY_4("./sounds/buy4.wav"),
    BUTTON_CLICK("./sounds/button_click.wav");

    private final String path;

    Sound(String path) {
        this.path = path;
    }

    public void play() {
        SoundPlayer.play(path);
    }

    // Overload to allow game setting to activate or deactivate sfx
    public void play(boolean isActive) {
        if (isActive) {
            SoundPlayer.play(path);
        }
    }

    public void playLoop() {
        SoundPlayer.playLoop(path);
    }

    // Overload to allow game setting to activate or deactivate sfx
    public ClipHandler playLoop(boolean isActive) {
        ClipHandler clipHandler = null;
        if (isActive) {
            clipHandler = SoundPlayer.playLoop(path);
        }
        return clipHandler;
    }

    public String getPath() {
        return path;
    }
}
