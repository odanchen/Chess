package root.ui.frames.game_frame.panels;

import root.logic.pieces.ChessPiece;
import root.logic.pieces.Pawn;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DragPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    private MouseEvent mouse;
    public DragPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (mouse != null && gameStatus.getSelectedPiece() != null) drawPiece(g,mouse);
    }

    private void drawPiece(Graphics g, MouseEvent e) {
        int row = e.getY() - graphicsManager.getSquareSize();
        int col = e.getX() - graphicsManager.getSquareSize();

        BufferedImage image = graphicsManager.getTextureOfPiece(gameStatus.getSelectedPiece());
        g.drawImage(image, col, row, null);
    }

    public void onNewMouseEvent(MouseEvent e) {
        mouse = e;
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        mouse = null;
        repaint();
    }

}
