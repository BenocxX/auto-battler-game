package cegepst.engine.resources.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class ClipHandler {

    private static final ClassLoader classLoader = ClipHandler.class.getClassLoader();
    private final String path;
    private final Clip clip;

    public ClipHandler(String path) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.path = path;
        clip = AudioSystem.getClip();
        clip.open(getAudioStream());
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void start() {
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.flush();
        clip.close();
    }

    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(double volume) {
        if (volume < 0 || volume > 1)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    private AudioInputStream getAudioStream() throws IOException, UnsupportedAudioFileException {
        return AudioSystem.getAudioInputStream(getInputStream());
    }

    private InputStream getInputStream() {
        return classLoader.getResourceAsStream(path);
    }
}
