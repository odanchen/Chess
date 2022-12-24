package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.moves.AttackMove;
import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameStatus;


import javax.swing.*;
import java.awt.*;

public class IndicationPanel extends JPanel {
    private final double MOVE_SELECTION_TO_SQUARE_RATIO = 0.3;
    private final double ATTACK_SELECTION_TO_SQUARE_RATIO = 0.85;
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;

    public IndicationPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getPlayableRectangle());
        this.setOpaque(false);
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
                g.setColor(graphicsManager.getSelectionColor());
                if (move instanceof AttackMove && ((AttackMove) move).getAttackedPosition() == move.getEndPosition()) {
                    g2.setStroke(new BasicStroke(6));
                    g.drawOval(getAttackingCoordinate(col), getAttackingCoordinate(row), getAttackingOvalSize(), getAttackingOvalSize());
                } else
                    g.fillOval(getMovingCoordinate(col), getMovingCoordinate(row), getMovingOvalSize(), getMovingOvalSize());
            }
        }
    }
    private int getMovingOvalSize() {
        return (int) (graphicsManager.squareSize() * MOVE_SELECTION_TO_SQUARE_RATIO);
    }

    private int getMovingCoordinate(int idx) {
        return (graphicsManager.squareSize() * idx) + (graphicsManager.squareSize() - getMovingOvalSize()) / 2;
    }

    private int getAttackingOvalSize() {
        return (int) (graphicsManager.squareSize() * ATTACK_SELECTION_TO_SQUARE_RATIO);
    }

    private int getAttackingCoordinate(int idx) {
        return (graphicsManager.squareSize() * idx) + (graphicsManager.squareSize() - getAttackingOvalSize()) / 2;
    }
}
