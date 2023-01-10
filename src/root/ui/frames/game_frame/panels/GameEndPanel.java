package root.ui.frames.game_frame.panels;

import root.ui.graphics.GraphicsManager;
import root.ui.game_flow.GameResult;

import javax.swing.*;
import java.awt.*;

public class GameEndPanel extends JPanel {

    private final GraphicsManager graphicsManager;
    private GameResult gameResult;

    public GameEndPanel(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getEndPanelBounds());
        this.setOpaque(true);
        this.setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        int arcSize = graphicsManager.getSquareSize() / 2;
        g.setColor(Color.WHITE);
        g.fillRoundRect((int) (graphicsManager.getSquareSize() * 1.75), (int) (graphicsManager.getSquareSize() * 1.75), (int) (graphicsManager.getSquareSize() * 4.25), (int) (graphicsManager.getSquareSize() * 4.25), arcSize, arcSize);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Game Over", getBounds().x + (int) (getBounds().width / 3.5), getBounds().y + getBounds().height / 4);
        String result;
        if (gameResult == GameResult.PLAYER_WHITE_WON_BY_CHECKMATE) {
            result = "Player white won by a checkmate!";
        } else if (gameResult == GameResult.PLAYER_BLACK_WON_BY_CHECKMATE) {
            result = "Player black won by a checkmate!";
        } else {
            result = "Draw!";
        }
        g.drawString(result, getBounds().x + (int) (getBounds().width / 3.5), getBounds().y + getBounds().height / 2);
    }

    public void showPanel(GameResult gameResult) {
        this.setVisible(true);
        this.gameResult = gameResult;
    }
}
