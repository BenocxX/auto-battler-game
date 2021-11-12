package cegepst.game;

import cegepst.engine.sounds.SoundPlayer;

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

    public void playLoop() {
        SoundPlayer.playLoop(path);
    }
}
