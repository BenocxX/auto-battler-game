package cegepst.engine.resources.sounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class ClipHandler {

    private static final ClassLoader classLoader = ClipHandler.class.getClassLoader();
    private final String path;
    private final Clip clip;

    public ClipHandler(String path) throws LineUnavailableException {
        this.path = path;
        clip = AudioSystem.getClip();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void start() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.open(getAudioStream());
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.flush();
        clip.close();
    }

    private AudioInputStream getAudioStream() throws IOException, UnsupportedAudioFileException {
        return AudioSystem.getAudioInputStream(getInputStream());
    }

    private InputStream getInputStream() {
        return classLoader.getResourceAsStream(path);
    }
}
