package ui.frames.game_frame.panels.dragging;

import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PieceDraggedPanel extends JPanel {

    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;

    public PieceDraggedPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(0,0,graphicsManager.getSquareSize(),graphicsManager.getSquareSize());
        this.setOpaque(false);
        this.setVisible(true);
    }

    public void pieceMoved(MouseEvent e) {
        setVisible(true);
        setBounds(e.getX() - graphicsManager.getSquareSize(),e.getY() - graphicsManager.getSquareSize(),graphicsManager.getSquareSize(),graphicsManager.getSquareSize());
    }

    public void clean() {
        setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        if (gameStatus.isPieceSelected()) {
            BufferedImage image = graphicsManager.getTextureOfPiece(gameStatus.getSelectedPiece());
            g.drawImage(image, 0, 0, null);
        }
    }

}
