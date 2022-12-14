package BoardPackage;

import java.awt.Color;

class ColorPair {
    Color whiteCell;
    Color blackCell;
    ColorPair(Color whiteCell, Color blackCell) {
        this.whiteCell = whiteCell;
        this.blackCell = blackCell;
    }
}
class BoardColors {
    public static ColorPair OPTION1 = new ColorPair(new Color(225, 198, 153), new Color(150, 75, 0));
}
