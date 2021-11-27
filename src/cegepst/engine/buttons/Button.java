package cegepst.engine.buttons;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Button extends StaticEntity {

    protected static final Color SELECTED_COLOR = new Color(132, 132, 132, 255);
    protected static final Color UNSELECTED_COLOR = new Color(108, 108, 108, 255);

    protected String text;
    protected boolean isClicked;
    protected boolean isHovered;
    protected boolean isSelected;
    protected Callback customEvent;

    /*
     * Lots of overloaded constructors because I want to let
     * the developer customize the button easily.
     */
    public Button(int x, int y, int width, int height, String text, Callback event) {
        teleport(x, y);
        setDimension(width, height);
        this.text = text;
        this.customEvent = event;
    }

    public Button(int x, int y, int style, String text, Callback event) {
        teleport(x, y);
        applyStyle(style);
        this.text = text;
        this.customEvent = event;
    }

    public Button(int x, int y, int style, String text) {
        teleport(x, y);
        applyStyle(style);
        this.text = text;
    }

    public Button(int x, int y, String text) {
        teleport(x, y);
        applyStyle(ButtonStyle.MEDIUM_HORIZONTAL);
        this.text = text;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isHovered || isSelected) {
            buffer.drawRectangle(x, y, width, height, SELECTED_COLOR);
        } else {
            buffer.drawRectangle(x, y, width, height, UNSELECTED_COLOR);
        }
        buffer.drawCenteredText(text, getBounds());
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isClicked(Point mousePosition) {
        isClicked = getBounds().contains(mousePosition);
        return isClicked;
    }

    public void callback() {
        customEvent.callback();
    }

    public void setCustomEvent(Callback customEvent) {
        this.customEvent = customEvent;
    }

    public boolean isHovered(Point mousePosition) {
        isHovered = getBounds().contains(mousePosition);
        return isHovered;
    }

    public void isSelected(boolean isSelected) {
        this.isSelected = isSelected;
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
