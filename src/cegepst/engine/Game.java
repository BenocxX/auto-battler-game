package cegepst.engine;

public abstract class Game {

    private boolean playing = true;
    private RenderingEngine renderingEngine;

    public abstract void initialize();
    public abstract void update();
    public abstract void draw(Buffer buffer);
    public abstract void conclude();

    public Game() {
        renderingEngine = RenderingEngine.getInstance();
    }

    public final void start() {
        initialize();
        run();
        conclude();
    }

    public final void stop() {
        playing = false;
    }

    private void run() {
        renderingEngine.start();
        GameTime.getInstance();
        while (playing) {
            update();
            if (playing) {
                draw(renderingEngine.getRenderingBuffer());
                renderingEngine.renderBufferOnScreen();
                GameTime.getInstance().synchronize();
            }
        }
        renderingEngine.stop();
    }
}
