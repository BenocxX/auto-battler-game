package cegepst.engine;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Buffer {

    private final Graphics2D graphics;
    private Font font;

    public Buffer(Graphics2D graphics) {
        this.graphics = graphics;
        font = new Font("Arial", Font.PLAIN, 20);
    }

    public void drawRectangle(int x, int y, int width, int height, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRect(x, y, width, height);
    }

    public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
    }

    public void drawCircle(int x, int y, int radius, Paint paint) {
        graphics.setPaint(paint);
        graphics.fillOval(x, y, radius * 2, radius * 2);
    }

    public void drawText(String text, int x, int y, Paint paint) {
        graphics.setPaint(paint);
        graphics.drawString(text, x, y);
    }

    // Source: https://stackoverflow.com/a/27740330
    public void drawCenteredText(String text, Rectangle rect) {
        FontMetrics metrics = graphics.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        graphics.setFont(font);
        drawText(text, x, y, Color.WHITE);
    }

    public void drawGameDebugStats() {
        drawText("FPS: " + GameTime.getCurrentFps(), 10, 20, Color.WHITE);
        drawText(GameTime.getElapsedTimeFormattedTime(), 10, 40, Color.WHITE);
    }

    public void drawImage(Image image, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }

    public void setFontSize(int style, int fontSize) {
        font = new Font("Arial", style, fontSize);
        graphics.setFont(font); //http://gauss.ececs.uc.edu/Users/Franco/Fonts/Fonts.java.html
    }
}
