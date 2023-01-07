package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameResult;

import javax.swing.*;
import java.awt.*;

public class GameEndPanel extends JPanel {

    private final GraphicsManager graphicsManager;
    private GameResult gameResult;

    public GameEndPanel(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getBoardPanelBounds());
        this.setOpaque(true);
        this.setVisible(false);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        Rectangle endRect = endRect();
        g.fillRoundRect(endRect.x,endRect.y,endRect.width,endRect.height,20,20);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Game Over",endRect.x + (int) (endRect.width/3.5),endRect.y + endRect.height/4);
        String result;
        if (gameResult == GameResult.PLAYER_WHITE_WON_BY_CHECKMATE) {
            result = "White won!";
        } else if (gameResult == GameResult.PLAYER_BLACK_WON_BY_CHECKMATE) {
            result = "Black won!";
        } else {
            result = "Draw!";
        }
        g.drawString(result,endRect.x + (int) (endRect.width/3.5),endRect.y + endRect.height/2);

    }

    public void switchVis(GameResult gameResult) {
        this.setVisible(!this.isVisible());
        this.gameResult = gameResult;
    }

    private Rectangle endRect() {
        Rectangle playArea = graphicsManager.getPlayAreaBounds();
        int squareSize = graphicsManager.getSquareSize();
        return new Rectangle(playArea.x + (int) (squareSize * 2.75),playArea.y + (int) (squareSize * 2.05),(int) (squareSize * 2.5),(int) (squareSize * 3.9));
    }

}
