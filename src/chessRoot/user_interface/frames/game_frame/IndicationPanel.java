package chessRoot.user_interface.frames.game_frame;

import chessRoot.assets.board_colors.ColorSet;
import chessRoot.logic.moves.AttackMove;
import chessRoot.logic.pieces.ChessPiece;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStatus;


import javax.swing.*;
import java.awt.*;

public class IndicationPanel extends JPanel {
    ChessPiece selectedPiece;
    private final double MOVE_SELECTION_TO_SQUARE_RATIO = 0.35;
    private final double ATTACK_SELECTION_TO_SQUARE_RATIO = 0.85;
    private int boardSize;
    private ColorSet colorSet;
    private final GameStatus gameStatus;
    private final GameControl gameControl;

    public IndicationPanel(int boardSize, GameStatus gameStatus, GameControl gameControl, ColorSet colorSet) {
        setSize(boardSize, boardSize);
        setOpaque(false);
        this.boardSize = boardSize;
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.colorSet = colorSet;
    }

    @Override
    public void paint(Graphics g) {
        if (gameStatus.isPieceSelected()) {
            for (var move : gameStatus.getSelectedPieceMoves()) {
                int row = Math.abs(move.getEndPosition().getRow() - 8);
                int col = (int) move.getEndPosition().getCol() - 'a';
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(colorSet.getCellSelection());
                if (move instanceof AttackMove) {
                    g2.setStroke(new BasicStroke(6));
                    g.drawOval(getAttackingCoordinate(col), getAttackingCoordinate(row), getAttackingOvalSize(), getAttackingOvalSize());
                } else g.fillOval(getMovingCoordinate(col), getMovingCoordinate(row), getMovingOvalSize(), getMovingOvalSize());
            }
        }
    }
    private int getSquareSize() {
        return this.boardSize / 8;
    }

    public void updateSelectedPiece(ChessPiece piece) {
        this.selectedPiece = piece;
        this.repaint();
    }

    private int getMovingOvalSize() {
        return (int) (getSquareSize() * MOVE_SELECTION_TO_SQUARE_RATIO);
    }
    private int getMovingCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize()-getMovingOvalSize())/2;
    }

    private int getAttackingOvalSize() {
        return (int) (getSquareSize() * ATTACK_SELECTION_TO_SQUARE_RATIO);
    }
    private int getAttackingCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize()-getAttackingOvalSize())/2;
    }
}