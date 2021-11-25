package cegepst.engine.controls;

import cegepst.engine.RenderingEngine;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MousePad extends MouseAdapter {

    private boolean isClicked;

    public MousePad() {
        RenderingEngine.getInstance().addMouseListener(this);
    }

    public Point getMousePosition() {
        Point mousePositionRelativeToScreen = MouseInfo.getPointerInfo().getLocation();
        Point locationOfJPanelOnScreen = RenderingEngine.getInstance().getLocationOnScreen();
        return new Point(mousePositionRelativeToScreen.x - locationOfJPanelOnScreen.x,
                mousePositionRelativeToScreen.y - locationOfJPanelOnScreen.y);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void resetIsClicked() {
        isClicked = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = true;
    }
}
