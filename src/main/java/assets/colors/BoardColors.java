package assets.colors;

import java.awt.*;

/**
 * A class which stores color sets for the board;
 */

public class BoardColors {
    public static final ColorSet OPTION1 = new ColorSet(new Color(225, 198, 153), new Color(150, 75, 0), new Color(28, 28, 26, 50), "Brown");
    public static final ColorSet OPTION2 = new ColorSet(new Color(238, 238, 210), new Color(118, 150, 86), new Color(28, 28, 26, 50), "Green");
    public static final ColorSet OPTION3 = new ColorSet(new Color(232, 235, 239), new Color(125, 135, 150), new Color(28, 28, 26, 50), "Gray");

    /**
     * @param signature The signature color set. A color set is a theme for the game, including the board colors.
     * @return The corresponding color set.
     */
    public static ColorSet getColors(String signature) {
        switch (signature) {
            case "Brown":
                return OPTION1;
            case "Green":
                return OPTION2;
            default:
                return OPTION3;
        }
    }
}
