package root.ui.frames.game_frame.panels;

import root.logic.pieces.ChessPiece;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PiecePanel extends JPanel {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private ChessPiece undrawnPiece = null;

    public PiecePanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        gameStatus.getAllPieces().forEach(piece -> drawPiece(g, piece));
    }

    private void drawPiece(Graphics g, ChessPiece piece) {
        int row = Math.abs(piece.getPosition().getRow() - (graphicsManager.isFlipped() ? 1 : 8));
        int col = Math.abs(piece.getPosition().getCol() - 'a' - (graphicsManager.isFlipped() ? 7 : 0));

        BufferedImage image = graphicsManager.getTextureOfPiece(piece);
        if (!piece.equals(undrawnPiece))
            g.drawImage(image, graphicsManager.getPieceCoordinate(col), graphicsManager.getPieceCoordinate(row), null);
    }

    public void setUndrawnPosition(ChessPiece chessPiece) {
        undrawnPiece = chessPiece;
    }

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getPlayAreaBounds().getSize();
    }
}