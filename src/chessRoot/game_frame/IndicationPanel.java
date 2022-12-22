package chessRoot.game_frame;

import chessRoot.Board;
import chessRoot.pieces.ChessPiece;

import javax.swing.*;
import java.awt.*;

public class IndicationPanel extends JPanel {
    ChessPiece selectedPiece;
    private final double SELECTION_TO_SQUARE_RATIO = 0.875;
    private int boardSize;
    Board board;

    public IndicationPanel(int boardSize, Board board) {
        this.boardSize = boardSize;
        this.board = board;
    }

    @Override
    public void paint(Graphics g) {
        for (var move : selectedPiece.calculateMoves(board)) {
            int row = move.getEndPosition().getRow();
            int col = (int) move.getEndPosition().getCol() - 'a';
        }
    }

    private void updateSelectedPiece(ChessPiece piece) {
        this.selectedPiece = piece;
    }
    private int getCoordinate(int idx) {
        return (getSquareSize() * idx);
    }
    private int getSquareSize() {
        return this.boardSize / 8;
    }

}
