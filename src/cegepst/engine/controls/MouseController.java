package cegepst.engine.controls;

import cegepst.engine.RenderingEngine;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public abstract class MouseController extends MouseAdapter {

    private HashMap<Integer, Boolean> clickedButtons;

    public MouseController() {
        clickedButtons = new HashMap<>();
        RenderingEngine.getInstance().addMouseListener(this);
    }

    protected boolean isClicked(int button) {
        return clickedButtons.get(button);
    }

    protected void bindButtons(int[] buttons) {
        for (int button : buttons) {
            clickedButtons.put(button, false);
        }
    }

    protected void bindButton(int button) {
        clickedButtons.put(button, false);
    }

    protected Point getMousePositionRelativeToScreen() {
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        Point locationOfJPanelOnScreen = RenderingEngine.getInstance().getLocationOnScreen();
        return new Point(mousePosition.x - locationOfJPanelOnScreen.x,
                mousePosition.y - locationOfJPanelOnScreen.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
        if (clickedButtons.containsKey(button)) {
            clickedButtons.put(button, true);
        }
    }

    public void resetClickedButtons() {
        clickedButtons.replaceAll((k, v) -> false);
    }
}
