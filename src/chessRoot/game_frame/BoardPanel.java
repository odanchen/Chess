package chessRoot.game_frame;

import chessRoot.assets.board_colors.BoardColors;
import chessRoot.assets.board_colors.ColorSet;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int boardSize;
    private ColorSet boardColors;


    private int squareSize() {
        return boardSize / 8;
    }

    @Override
    public void paint(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((col + row) % 2 == 0) g.setColor(boardColors.getWhiteCell());
                else g.setColor(boardColors.getBlackCell());

                g.fillRect(col * squareSize(), row * squareSize(), squareSize(), squareSize());
            }
        }
    }

    BoardPanel(int boardSideSize, ColorSet colorSet) {
        this.setSize(boardSideSize, boardSideSize);
        this.setOpaque(true);
        this.boardSize = boardSideSize;
        this.boardColors = colorSet;
    }
}
