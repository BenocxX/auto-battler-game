package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Button extends StaticEntity {

    private String text;
    private boolean isClicked;
    private boolean isHovered;

    public Button(int x, int y, int width, int height, String text) {
        teleport(x, y);
        setDimension(width, height);
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isHovered) {
            buffer.drawRectangle(x, y, width, height, new Color(132, 132, 132, 255));
        } else {
            buffer.drawRectangle(x, y, width, height, new Color(108, 108, 108, 255));
        }
        buffer.drawCenteredText(text, getBounds());
    }

    public boolean isClicked(Point mousePosition) {
        isClicked = getBounds().contains(mousePosition);
        return isClicked;
    }

    public void checkIfHovered(Point mousePosition) {
        isHovered = getBounds().contains(mousePosition);
    }
}
