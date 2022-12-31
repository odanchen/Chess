package chessRoot.user_interface.frames.game_frame;

import chessRoot.logic.moves.Move;
import chessRoot.logic.pieces.PieceColor;
import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PromotionPanel extends JPanel {

    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;

    public PromotionPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        Move move = gameStatus.getSelectedMove();
        if (move != null) {
            drawPanel(g, move);
        }
    }

    private void drawPanel(Graphics g, Move move) {
        int sqSize = graphicsManager.getSquareSize();
        int col = move.getEndPosition().getCol() - 'a';
        int row = Math.abs(move.getEndPosition().getRow() - 8) - (move.getPieceAtStart(gameStatus.getBoard()).getPieceColor() == PieceColor.WHITE ? 0 : 3);
        if (graphicsManager.isFlipped()) {
            col = Math.abs(col - 7);
            row = Math.abs(row - 4);
        }
        drawBackground(col, row, sqSize, g);
        drawPieces(col, row, sqSize, g);
    }

    private void drawBackground(int col, int row, int sqSize, Graphics g) {
        g.setColor(Color.white);
        g.fillRoundRect(col * sqSize, row * sqSize, sqSize, sqSize * 4, sqSize / 3, sqSize / 3);
    }

    public void drawPieces(int col, int row, int sqSize, Graphics g) {
        List<BufferedImage> pieces = new ArrayList<>();
        String color = gameStatus.getSelectedColor().getColorSign();
        pieces.add(graphicsManager.getTextureOfPiece(color + "q"));
        pieces.add(graphicsManager.getTextureOfPiece(color + "n"));
        pieces.add(graphicsManager.getTextureOfPiece(color + "r"));
        pieces.add(graphicsManager.getTextureOfPiece(color + "b"));
        if ((color.equals("b") && !graphicsManager.isFlipped()) || (color.equals("w") && graphicsManager.isFlipped()))
            Collections.reverse(pieces);
        for (int i = 0; i < pieces.size(); i++) {
            g.drawImage(pieces.get(i), graphicsManager.getPieceCoordinate(col), graphicsManager.getPieceCoordinate(row) + i * sqSize, null);
        }
    }

    public void updatePanel() {
        removeAll();
        repaint();
    }
}
