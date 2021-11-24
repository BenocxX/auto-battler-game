package cegepst.engine.buttons;

import cegepst.engine.Buffer;

public class RoundButton extends Button {

    private int arcWidth;
    private int arcHeight;

    /*
     * Lots of overloaded constructors because I want to let
     * the developer customize the button easily.
     */
    public RoundButton(int x, int y, int width, int height,
                       int arcWidth, int arcHeight,
                       String text, Callback event) {
        super(x, y, width, height, text, event);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    public RoundButton(int x, int y, int style, String text, Callback event) {
        super(x, y, style, text, event);
        applyStyle(style);
    }

    public RoundButton(int x, int y, int style, String text) {
        super(x, y, style, text);
        applyStyle(style);
    }

    public RoundButton(int x, int y, String text) {
        super(x, y, text);
        applyStyle(ButtonStyle.MEDIUM_HORIZONTAL_ROUND);
    }

    @Override
    public void draw(Buffer buffer) {
        if (isHovered || isSelected) {
            buffer.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight, SELECTED_COLOR);
        } else {
            buffer.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight, UNSELECTED_COLOR);
        }
        buffer.drawCenteredText(text, getBounds());
    }

    @Override
    protected void applyStyle(int style) {
        switch (style) {
            case 4 -> {
                width = 100;
                height = 25;
                arcWidth = 20;
                arcHeight = 20;
            }
            case 5 -> {
                width = 200;
                height = 50;
                arcWidth = 30;
                arcHeight = 30;
            }
            case 6 -> {
                width = 400;
                height = 100;
                arcWidth = 40;
                arcHeight = 40;
            }
            case 10 -> {
                width = 25;
                height = 100;
                arcWidth = 20;
                arcHeight = 20;
            }
            case 11 -> {
                width = 50;
                height = 200;
                arcWidth = 30;
                arcHeight = 30;
            }
            case 12 -> {
                width = 100;
                height = 400;
                arcWidth = 40;
                arcHeight = 40;
            }
        }
        if (width != 0) {
            super.applyStyle(style);
        }
    }
}
