package cegepst.game.resources;

import cegepst.engine.resources.sounds.ClipHandler;
import cegepst.engine.resources.sounds.SoundPlayer;
import cegepst.game.GameSettings;

public class MusicHandler {

    private final ClipHandler musicClip;

    public MusicHandler() {
        musicClip = SoundPlayer.getClip(Sound.MUSIC.getPath());
    }

    public void play() {
        setVolumeBasedOnGameSettings(GameSettings.MUSIC);
        musicClip.start();
        musicClip.loop();
    }

    public void stop() {
        musicClip.stop();
    }

    public void mute() {
        musicClip.setVolume(0);
    }

    public void unmute() {
        musicClip.setVolume(1);
    }

    public void setVolumeBasedOnGameSettings(boolean isMusicOn) {
        if (isMusicOn) {
            unmute();
        } else {
            mute();
        }
    }
}
