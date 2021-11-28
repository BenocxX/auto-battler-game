package cegepst.game.helpers;

import java.awt.*;

public class CenteringMachine {

    public static int centerHorizontally(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.x + ((rectangle1.width - rectangle2.width) / 2);
    }

    public static int centerHorizontally(Rectangle rectangle1, int widthRect2) {
        return rectangle1.x + ((rectangle1.width - widthRect2) / 2);
    }

    public static int centerVertically(Rectangle rectangle1, Rectangle rectangle2) {
        return rectangle1.y + ((rectangle1.height - rectangle2.height) / 2);
    }

    public static int centerVertically(Rectangle rectangle1, int height) {
        return rectangle1.y + ((rectangle1.height - height) / 2);
    }
}
