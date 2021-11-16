package cegepst.engine;

import java.awt.*;

public class Buffer {

    private final Graphics2D graphics;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawText(String text, int x, int y, Paint paint) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 14));
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    public void drawGameDebugStats() {
        drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        drawText(GameTime.getElapsedTimeFormattedTime(), 10, 40, Color.WHITE);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public void setFontSize(int style, int fontSize) {
        graphics.setFont(new Font("TimesRoman", style, fontSize)); //http://gauss.ececs.uc.edu/Users/Franco/Fonts/Fonts.java.html
    }
}
