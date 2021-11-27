package cegepst.game.controls;

import cegepst.engine.RenderingEngine;
import cegepst.engine.controls.MouseController;

import java.awt.event.MouseEvent;

public class MousePad extends MouseController {

    private int leftClick = MouseEvent.BUTTON1;
    private int middleClick = MouseEvent.BUTTON2;
    private int rightClick = MouseEvent.BUTTON3;

    public MousePad() {
        RenderingEngine.getInstance().addMouseListener(this);
        bindButtons(new int[] {
                leftClick,
                middleClick,
                rightClick
        });
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
