package ui.frames.game_frame.panels.dragging;

import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DragPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    private MouseEvent mouse;
    private final JPanel piecePanel;

    public DragPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
        this.piecePanel = new PieceDraggedPanel(graphicsManager,gameStatus);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (mouse != null && gameStatus.getSelectedPiece() != null) drawPiece(mouse);
    }

    private void drawPiece(MouseEvent e) {
        int row = e.getY() - graphicsManager.getSquareSize();
        int col = e.getX() - graphicsManager.getSquareSize();
        piecePanel.setBounds(col,row,graphicsManager.getSquareSize(),graphicsManager.getSquareSize());
    }

    public void onNewMouseEvent(MouseEvent e) {
        mouse = e;
        repaint();
    }

    public void mouseReleased() {
        mouse = null;
        repaint();
    }
}
