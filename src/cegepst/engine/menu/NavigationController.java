package cegepst.engine.menu;

import cegepst.engine.menu.buttons.RoundButton;
import cegepst.engine.helpers.LoopingIndex;

import java.util.ArrayList;

public abstract class NavigationController {

    protected ArrayList<RoundButton> buttons;
    protected LoopingIndex loopingIndex;

    public abstract void inputCheck(ArrayList<RoundButton> buttons, LoopingIndex loopingIndex);
}
