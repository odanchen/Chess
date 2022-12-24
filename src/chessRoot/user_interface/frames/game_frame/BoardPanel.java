package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final GraphicsManager graphicsManager;

    @Override
    public void paint(Graphics g) {
        drawBoardEdge(g);
        int square = graphicsManager.getSquareSize();
        int edge = graphicsManager.getEdgeSize();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((col + row) % 2 == 0) g.setColor(graphicsManager.getWhiteSquareColor());
                else g.setColor(graphicsManager.getBlackSquareColor());

                g.fillRect(col * square + edge, row * square + edge, square, square);
            }
        }
    }

    private void drawBoardEdge(Graphics g) {
        int board = graphicsManager.getBoardSize();
        int edge = graphicsManager.getEdgeSize();
        g.setColor(graphicsManager.getBlackSquareColor());
        g.fillRect(0, 0, board + 2 * edge, edge);
        g.fillRect(0, board + edge, board + 2 * edge, edge);
        g.fillRect(0, edge, edge, board);
        g.fillRect(board + edge, edge, edge, board);
        drawOutline(g);
    }

    private void drawOutline(Graphics g) {
        int board = graphicsManager.getBoardSize();
        int edge = graphicsManager.getEdgeSize();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(graphicsManager.getOutlineSize()));
        g2d.setColor(graphicsManager.getWhiteSquareColor());
        g2d.drawRect(edge, edge, board, board);
    }

    BoardPanel(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getGameFrameBounds());
        this.setOpaque(true);
    }
}
