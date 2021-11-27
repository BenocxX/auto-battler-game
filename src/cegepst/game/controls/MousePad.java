package cegepst.game.controls;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MouseController;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MousePad extends MouseController {

    private final int leftClick = MouseEvent.BUTTON1;
    private final int middleClick = MouseEvent.BUTTON2;
    private final int rightClick = MouseEvent.BUTTON3;

    public MousePad() {
        RenderingEngine.getInstance().addMouseListener(this);
        bindButtons(new int[] {
                leftClick,
                middleClick,
                rightClick
        });
    }

    public Point getPosition() {
        return getMousePositionRelativeToScreen();
    }

    public boolean isLeftClicked() {
        return isClicked(leftClick);
    }

    public boolean isMiddleClicked() {
        return isClicked(middleClick);
    }

    public boolean isRightClicked() {
        return isClicked(rightClick);
    }
}
