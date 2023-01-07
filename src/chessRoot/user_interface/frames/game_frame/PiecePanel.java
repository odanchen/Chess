package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.pieces.ChessPiece;
import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PiecePanel extends JPanel {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;

    public void updatePanel() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        gameStatus.getAllPieces().forEach(piece -> drawPiece(g, piece));
    }

    private void drawPiece(Graphics g, ChessPiece piece) {
        int row = Math.abs(piece.getPosition().getRow() - (graphicsManager.isFlipped() ? 1 : 8));
        int col = Math.abs(piece.getPosition().getCol() - 'a' - (graphicsManager.isFlipped() ? 7 : 0));

        BufferedImage image = graphicsManager.getTextureOfPiece(piece);
        g.drawImage(image, graphicsManager.getPieceCoordinate(col), graphicsManager.getPieceCoordinate(row), null);
    }

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getPlayAreaBounds().getSize();
    }

    PiecePanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
    }
}