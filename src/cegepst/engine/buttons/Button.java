package cegepst.engine.buttons;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Button extends StaticEntity {

    private String text;
    private boolean isClicked;
    private boolean isHovered;
    private boolean isVisible;
    private CustomEvent event;

    public Button(int x, int y,
                  int width, int height,
                  String text, boolean isVisible,
                  CustomEvent event) {
        teleport(x, y);
        setDimension(width, height);
        this.text = text;
        this.event = event;
        this.isVisible = isVisible;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isVisible) {
            if (isHovered) {
                buffer.drawRoundRectangle(x, y, width, height, 30, 30, new Color(132, 132, 132, 255));
            } else {
                buffer.drawRoundRectangle(x, y, width, height, 30, 30, new Color(108, 108, 108, 255));
            }
            buffer.drawCenteredText(text, getBounds());
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isClicked(Point mousePosition) {
        isClicked = getBounds().contains(mousePosition);
        return isClicked;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void event() {
        event.activate();
    }

    public void checkIfHovered(Point mousePosition) {
        isHovered = getBounds().contains(mousePosition);
    }
}
