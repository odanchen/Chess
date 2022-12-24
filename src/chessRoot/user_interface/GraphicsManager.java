package chessRoot.user_interface;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;

import java.awt.*;

public class GraphicsManager {
    private ColorSet boardColors;
    private int boardSize;

    public int getBoardSize() {
        return boardSize;
    }

    public int getOutlineSize() {
        return squareSize() / 12;
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

    public int squareSize() {
        return getBoardSize() / 8;
    }

    public int rectangleSize() {
        return squareSize() / 3;
    }

    public Rectangle getPlayableRectangle() {
        return new Rectangle(rectangleSize(), rectangleSize(), getBoardSize(), getBoardSize());
    }

    public Rectangle getFrameRectangle() {
        int newRectSize = 2 * rectangleSize() + getBoardSize();
        return new Rectangle(0, 0, newRectSize, newRectSize);
    }

    public GraphicsManager() {
        boardSize = 512;
        boardColors = BoardColors.OPTION1;
    }
}
