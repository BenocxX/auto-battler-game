package cegepst.engine.buttons;

import cegepst.engine.Buffer;

import java.awt.*;

public class RoundButton extends Button {

    private int arcWidth;
    private int arcHeight;

    public RoundButton(int x, int y, int width, int height,
                       int arcWidth, int arcHeight, String text,
                       boolean isVisible, CustomEvent event) {
        super(x, y, width, height, text, isVisible, event);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    public RoundButton(int x, int y, int style, String text,
                       boolean isVisible, CustomEvent event) {
        super(x, y, style, text, isVisible, event);
        applyStyle(style);
    }

    @Override
    public void draw(Buffer buffer) {
        if (isVisible) {
            if (isHovered) {
                buffer.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight, new Color(132, 132, 132, 255));
            } else {
                buffer.drawRoundRectangle(x, y, width, height, arcWidth, arcHeight, new Color(108, 108, 108, 255));
            }
            buffer.drawCenteredText(text, getBounds());
        }
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