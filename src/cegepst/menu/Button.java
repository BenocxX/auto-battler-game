package cegepst.menu;

import cegepst.engine.Buffer;
import cegepst.engine.entities.StaticEntity;
import cegepst.engine.triggers.Trigger;
import cegepst.engine.triggers.Triggerable;

import java.awt.*;

public class Button extends StaticEntity implements Triggerable {

    private String text;
    private boolean isSelected;

    public Button(int x, int y, int width, int height, String text) {
        teleport(x, y);
        setDimension(width, height);
        this.text = text;
    }

    @Override
    public void draw(Buffer buffer) {
        if (isSelected) {
            buffer.drawRectangle(x, y, width, height, new Color(132, 132, 132, 255));
        } else {
            buffer.drawRectangle(x, y, width, height, new Color(108, 108, 108, 255));
        }
        buffer.drawCenteredText(text, getBounds());
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public void trigger() {
        isSelected = true;
    }

    @Override
    public void untrigger() {
        isSelected = false;
    }

    public Trigger generateTrigger() {
        Trigger trigger = new Trigger();
        trigger.teleport(x, y);
        trigger.setDimension(width, height);
        return trigger;
    }
}
