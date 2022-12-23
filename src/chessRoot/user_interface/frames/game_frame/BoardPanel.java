package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private int boardSize;
    private final GameStatus gameStatus;

    private int squareSize() {
        return boardSize / 8;
    }

    @Override
    public void paint(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((col + row) % 2 == 0) g.setColor(gameStatus.getBoardColors().getWhiteCell());
                else g.setColor(gameStatus.getBoardColors().getBlackCell());

                g.fillRect(col * squareSize(), row * squareSize(), squareSize(), squareSize());
            }
        }
    }

    BoardPanel(int boardSideSize, GameStatus gameStatus) {
        this.setSize(boardSideSize, boardSideSize);
        this.setOpaque(true);
        this.boardSize = boardSideSize;
        this.gameStatus = gameStatus;
    }
}
