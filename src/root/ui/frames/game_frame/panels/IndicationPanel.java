package root.ui.frames.game_frame.panels;

import root.logic.moves.AttackMove;
import root.logic.moves.Move;
import root.ui.graphics.GraphicsManager;
import root.ui.game_flow.GameStatus;


import javax.swing.*;
import java.awt.*;

public class IndicationPanel extends JPanel {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;

    public IndicationPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
    }

    public void updatePanel() {
        removeAll();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (gameStatus.isPieceSelected()) {
            gameStatus.getSelectedPieceMoves().forEach(move -> drawMoveIndication(g, move));
        }
    }

    private void drawMoveIndication(Graphics g, Move move) {
        int row = Math.abs(move.getEndPosition().rowToIdx() - (graphicsManager.isFlipped() ? 7 : 0));
        int col = Math.abs(move.getEndPosition().colToIdx() - (graphicsManager.isFlipped() ? 7 : 0));
        g.setColor(graphicsManager.getSelectionColor());

        if (move instanceof AttackMove && ((AttackMove) move).getAttackedPosition() == move.getEndPosition())
            drawAttackMove(g, row, col);
        else drawMove(g, row, col);

    }

    private void drawAttackMove(Graphics g, int row, int col) {
        g.setColor(graphicsManager.getSelectionColor());
        int ovalSize = graphicsManager.getAttackingOvalSize();
        row = graphicsManager.getAttackingCoordinate(row);
        col = graphicsManager.getAttackingCoordinate(col);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(graphicsManager.getAttackingLineSize()));
        g.drawOval(col, row, ovalSize, ovalSize);
    }

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getPlayAreaBounds().getSize();
    }

    private void drawMove(Graphics g, int row, int col) {
        g.setColor(graphicsManager.getSelectionColor());
        int ovalSize = graphicsManager.getMovingOvalSize();
        row = graphicsManager.getMovingCoordinate(row);
        col = graphicsManager.getMovingCoordinate(col);
        g.fillOval(col, row, ovalSize, ovalSize);
    }
}
