package root.assets.colors;

import java.awt.*;

public class ColorSet {
    private final Color whiteCell;
    private final Color blackCell;
    private final Color cellSelection;
    private final String stringVal;

    ColorSet(Color whiteCell, Color blackCell, Color whiteCellSelection, String stringVal) {
        this.whiteCell = whiteCell;
        this.blackCell = blackCell;
        this.cellSelection = whiteCellSelection;
        this.stringVal = stringVal;
    }

    public Color getWhiteCell() {
        return whiteCell;
    }

    public Color getBlackCell() {
        return blackCell;
    }

    public Color getCellSelection() {
        return cellSelection;
    }

    public String getStringVal() {
        return stringVal;
    }
}
