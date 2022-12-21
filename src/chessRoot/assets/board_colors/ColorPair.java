package chessRoot.assets.board_colors;

import java.awt.*;

public class ColorPair {
    private final Color whiteCell;
    private final Color blackCell;

    public Color getWhiteCell() {
        return whiteCell;
    }

    public Color getBlackCell() {
        return blackCell;
    }



    ColorPair(Color whiteCell, Color blackCell) {
        this.whiteCell = whiteCell;
        this.blackCell = blackCell;
    }
}
