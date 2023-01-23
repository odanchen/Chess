package ui.frames.game_frame.panels.dragging;

import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PieceDraggedPanel extends JPanel implements ActionListener {

    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    private final int pieceSize;
    private final int offset;

    public PieceDraggedPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.pieceSize = graphicsManager.getPieceSize();
        this.offset = (int) (pieceSize * 0.075);
        clean();
        this.setOpaque(false);
        this.setVisible(true);
        Timer timer = new Timer(50, this);
        timer.start();
    }

    public void pieceMoved(MouseEvent e) {
        setBounds(e.getX()-pieceSize-offset,e.getY() - pieceSize-offset,pieceSize,pieceSize);
    }

    public void clean() {
        this.setBounds(-pieceSize,-pieceSize,pieceSize,pieceSize);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (gameStatus.isPieceSelected()) {
            BufferedImage image = graphicsManager.getTextureOfPiece(gameStatus.getSelectedPiece());
            g.drawImage(image, 0, 0, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
