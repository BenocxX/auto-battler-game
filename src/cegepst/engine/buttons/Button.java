package cegepst.engine.buttons;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Button extends StaticEntity {

    protected String text;
    protected boolean isClicked;
    protected boolean isHovered;
    protected boolean isVisible;
    protected CustomEvent event;

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

    public Button(int x, int y,
                  int style, String text,
                  boolean isVisible,
                  CustomEvent event) {
        teleport(x, y);
        applyStyle(style);
        this.text = text;
        this.event = event;
        this.isVisible = isVisible;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isVisible) {
            if (isHovered) {
                buffer.drawRectangle(x, y, width, height, new Color(132, 132, 132, 255));
            } else {
                buffer.drawRectangle(x, y, width, height, new Color(108, 108, 108, 255));
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

    protected void applyStyle(int style) {
        switch (style) {
            case 1 -> {
                width = 100;
                height = 25;
            }
            case 2 -> {
                width = 200;
                height = 50;
            }
            case 3 -> {
                width = 400;
                height = 100;
            }
            case 7 -> {
                width = 25;
                height = 100;
            }
            case 8 -> {
                width = 50;
                height = 200;
            }
            case 9 -> {
                width = 100;
                height = 400;
            }
        }
    }
}
