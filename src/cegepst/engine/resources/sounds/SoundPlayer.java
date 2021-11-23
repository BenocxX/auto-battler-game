package cegepst.engine.resources.sounds;


public class SoundPlayer {

    public static synchronized void play(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClipHandler player = new ClipHandler(path);
                    player.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static ClipHandler playLoop(final String path) {
        try {
            ClipHandler player = new ClipHandler(path);
            player.loop();
            player.start();
            return player;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ClipHandler getClip(final String path) {
        try {
            return new ClipHandler(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
