package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.moves.AttackMove;
import chessRoot.user_interface.game_flow.GameStatus;


import javax.swing.*;
import java.awt.*;

public class IndicationPanel extends JPanel {
    private final double MOVE_SELECTION_TO_SQUARE_RATIO = 0.35;
    private final double ATTACK_SELECTION_TO_SQUARE_RATIO = 0.85;
    private int boardSize;
    private final GameStatus gameStatus;

    public IndicationPanel(int boardSize, GameStatus gameStatus) {
        setSize(boardSize, boardSize);
        setOpaque(false);
        this.boardSize = boardSize;
        this.gameStatus = gameStatus;
    }

    public void updatePanel() {
        removeAll();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (gameStatus.isPieceSelected()) {
            for (var move : gameStatus.getSelectedPieceMoves()) {
                int row = move.getEndPosition().rowToIdx();
                int col = move.getEndPosition().colToIdx();
                Graphics2D g2 = (Graphics2D) g;
                g.setColor(gameStatus.getBoardColors().getCellSelection());
                if (move instanceof AttackMove) {
                    g2.setStroke(new BasicStroke(6));
                    g.drawOval(getAttackingCoordinate(col), getAttackingCoordinate(row), getAttackingOvalSize(), getAttackingOvalSize());
                } else
                    g.fillOval(getMovingCoordinate(col), getMovingCoordinate(row), getMovingOvalSize(), getMovingOvalSize());
            }
        }
    }

    private int getSquareSize() {
        return this.boardSize / 8;
    }

    private int getMovingOvalSize() {
        return (int) (getSquareSize() * MOVE_SELECTION_TO_SQUARE_RATIO);
    }

    private int getMovingCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize() - getMovingOvalSize()) / 2;
    }

    private int getAttackingOvalSize() {
        return (int) (getSquareSize() * ATTACK_SELECTION_TO_SQUARE_RATIO);
    }

    private int getAttackingCoordinate(int idx) {
        return (getSquareSize() * idx) + (getSquareSize() - getAttackingOvalSize()) / 2;
    }
}
