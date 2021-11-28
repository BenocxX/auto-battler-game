package cegepst.engine.buttons;

import cegepst.engine.helpers.LoopingIndex;

import java.util.ArrayList;

public abstract class NavigationController {

    protected ArrayList<RoundButton> buttons;
    protected LoopingIndex loopingIndex;

    public abstract void inputCheck(ArrayList<RoundButton> buttons, LoopingIndex loopingIndex);
}
