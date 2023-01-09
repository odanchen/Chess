package root.assets.colors;

import java.awt.*;

public class ColorSet {
    private final Color whiteCell;
    private final Color blackCell;
    private final Color cellSelection;

    public Color getWhiteCell() {
        return whiteCell;
    }

    public Color getBlackCell() {
        return blackCell;
    }

    public Color getCellSelection() {
        return cellSelection;
    }

    ColorSet(Color whiteCell, Color blackCell, Color whiteCellSelection) {
        this.whiteCell = whiteCell;
        this.blackCell = blackCell;
        this.cellSelection = whiteCellSelection;
    }
}
