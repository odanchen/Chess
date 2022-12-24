package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;

    @Override
    public void paint(Graphics g) {
        drawBoardEdge(g);
        int square = graphicsManager.squareSize();
        int rect = graphicsManager.rectangleSize();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if ((col + row) % 2 == 0) g.setColor(graphicsManager.getWhiteSquareColor());
                else g.setColor(graphicsManager.getBlackSquareColor());

                g.fillRect(col * square + rect, row * square + rect, square, square);
            }
        }
    }

    private void drawBoardEdge(Graphics g) {
        int board = graphicsManager.getBoardSize();
        int rect = graphicsManager.rectangleSize();
        g.setColor(graphicsManager.getBlackSquareColor());
        g.fillRect(0, 0, board + 2 * rect, rect);
        g.fillRect(0, board + rect, board + 2 * rect, rect);
        g.fillRect(0, rect, rect, board);
        g.fillRect(board + rect, rect, rect, board);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(graphicsManager.getOutlineSize()));
        g2d.setColor(graphicsManager.getWhiteSquareColor());
        g2d.drawRect(rect, rect, board, board);
    }

    BoardPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getFrameRectangle());
        this.setOpaque(true);
    }
}
