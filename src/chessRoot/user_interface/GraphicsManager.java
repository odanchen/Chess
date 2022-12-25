package chessRoot.user_interface;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;

import java.awt.*;

public class GraphicsManager {
    private ColorSet boardColors;
    private int boardSize;
    private boolean isFlipped = false;

    public int getBoardSize() {
        return boardSize;
    }

    public int getOutlineSize() {
        return getSquareSize() / 6;
    }

    public Color getWhiteSquareColor() {
        return boardColors.getWhiteCell();
    }

    public Color getBlackSquareColor() {
        return boardColors.getBlackCell();
    }

    public Color getSelectionColor() {
        return boardColors.getCellSelection();
    }

    public int getSquareSize() {
        return getBoardSize() / 8;
    }

    public int getEdgeSize() {
        return getSquareSize() / 3;
    }

    public Rectangle getPlayAreaBounds() {
        return new Rectangle(getEdgeSize(), getEdgeSize(), getBoardSize(), getBoardSize());
    }

    public Rectangle getGameFrameBounds() {
        int newRectSize = 2 * getEdgeSize() + getBoardSize();
        return new Rectangle(0, 0, newRectSize, newRectSize);
    }
    public boolean getFlipped() {
        return isFlipped;
    }
    public void flipBoard(boolean foo) {
        isFlipped = foo;
    }
    public void flipBoard() {
        isFlipped = !isFlipped;
    }

    public GraphicsManager() {
        boardSize = 512;
        boardColors = BoardColors.OPTION1;
    }
}
