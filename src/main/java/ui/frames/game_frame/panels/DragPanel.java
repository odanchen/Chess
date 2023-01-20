package ui.frames.game_frame.panels;

import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
        if (mouse != null && gameStatus.getSelectedPiece() != null) drawPiece(g, mouse);
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

    public void mouseReleased() {
        mouse = null;
        repaint();
    }
}
