package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    private static RenderingEngine instance;
    private Screen screen;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public static RenderingEngine getInstance() {
        if (instance == null) {
            instance = new RenderingEngine();
        }
        return instance;
    }

    public Buffer getRenderingBuffer() {
        bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return new Buffer(graphics);
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    public Screen getScreen() {
        return screen;
    }

    public void start() {
        screen.start();
    }

    public void stop() {
        screen.end();
    }

    public void addKeyListener(KeyListener listener) {
        panel.addKeyListener(listener);
    }

    public void addMouseListener(MouseListener listener) {
        panel.addMouseListener(listener);
    }

    public Point getLocationOnScreen() {
        return panel.getLocationOnScreen();
    }

    private RenderingEngine() {
        initializeScreen();
        initializePanel();
    }

    private void initializeScreen() {
        screen = new Screen();
        screen.setTitle("New Game");
        screen.setSize(WIDTH, HEIGHT);
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        screen.setPanel(panel);
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }
}
