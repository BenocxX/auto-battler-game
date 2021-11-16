package cegepst.game;

import cegepst.engine.resources.sounds.SoundPlayer;

public enum Sound {

    MUSIC("./musics/map1.wav"),
    ENEMY_1("./sounds/best1.wav");

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
    public void playLoop(boolean isActive) {
        if (isActive) {
            SoundPlayer.playLoop(path);
        }
    }
}
