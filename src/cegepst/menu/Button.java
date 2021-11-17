package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;

import java.awt.*;

public class Button extends StaticEntity {

    private String text;

    public Button(int x, int y, int width, int height, String text) {
        teleport(x, y);
        setDimension(width, height);
        this.text = text;
    }

    @Override
    public void draw(Buffer buffer) {
        buffer.drawRectangle(x, y, width, height, new Color(108, 108, 108, 255));
        buffer.drawCenteredText(text, getBounds());
    }
}
