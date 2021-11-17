package cegepst.engine.controls;

import cegepst.engine.RenderingEngine;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseController extends MouseAdapter {

    private boolean isClicked;

    public MouseController() {
        RenderingEngine.getInstance().addMouseListener(this);
    }

    public Point getMousePosition() {
        Point mousePositionRelativeToScreen = MouseInfo.getPointerInfo().getLocation();
        Point locationOfJPanelOnScreen = RenderingEngine.getInstance().getLocationOnScreen();
        return new Point(mousePositionRelativeToScreen.x - locationOfJPanelOnScreen.x,
                mousePositionRelativeToScreen.y - locationOfJPanelOnScreen.y);
    }

    public Rectangle getMouseRectangle() {
        Point mousePositionInJPanel = getMousePosition();
        Rectangle hitbox = new Rectangle(mousePositionInJPanel.x, mousePositionInJPanel.y, 1, 1);
        return hitbox;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void resetIsClicked() {
        isClicked = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        isClicked = true;
    }
}
