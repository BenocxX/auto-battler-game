package cegepst.game.resources;

import cegepst.engine.resources.sounds.ClipHandler;
import cegepst.engine.resources.sounds.SoundPlayer;
import cegepst.game.GameSettings;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MusicHandler {

    private ClipHandler musicClip;

    public MusicHandler() {
        musicClip = SoundPlayer.getClip(Sound.MUSIC.getPath());
    }

    public void play() {
        try {
            setVolumeBasedOnGameSettings(GameSettings.MUSIC);
            musicClip.start();
            musicClip.loop();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
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
