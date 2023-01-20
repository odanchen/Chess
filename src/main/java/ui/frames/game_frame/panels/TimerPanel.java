package ui.frames.game_frame.panels;

import ui.game_flow.GameResult;
import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel implements ActionListener {

    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    private final GamePanel gamePanel;

    public TimerPanel(GraphicsManager graphicsManager, GameStatus gameStatus, GamePanel gamePanel) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.gamePanel = gamePanel;
        this.setBounds(graphicsManager.getTimerPanelBounds());
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(graphicsManager.getBlackSquareColor());
        Rectangle topTimer = new Rectangle(0, 0, graphicsManager.getSquareSize() * 2, graphicsManager.getEdgeSize());
        Rectangle bottomTimer = new Rectangle(0, graphicsManager.getBoardSize() + graphicsManager.getEdgeSize() * 3, graphicsManager.getSquareSize() * 2, graphicsManager.getEdgeSize());

        g.fillRect(topTimer.x, topTimer.y + 1, topTimer.width, topTimer.height);
        g.fillRect(bottomTimer.x, bottomTimer.y, bottomTimer.width, bottomTimer.height);

        g.setColor(Color.BLACK);
        graphicsManager.drawCenteredString(g, (graphicsManager.isFlipped()) ? gameStatus.getWhiteTimeLeft() : gameStatus.getBlackTimeLeft(), topTimer);
        graphicsManager.drawCenteredString(g, (graphicsManager.isFlipped()) ? gameStatus.getBlackTimeLeft() : gameStatus.getWhiteTimeLeft(), bottomTimer);

        Timer timer = new Timer(1000, this);
        timer.start();
    }

    private void checkForEnd() {
        if (gameStatus.getWhiteTimeLeft().equals("00:00") && gameStatus.getGameResult() == null)
            gamePanel.endGame(GameResult.PLAYER_BLACK_WON_BY_TIME);
        else if (gameStatus.getBlackTimeLeft().equals("00:00") && gameStatus.getGameResult() == null)
            gamePanel.endGame(GameResult.PLAYER_WHITE_WON_BY_TIME);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        checkForEnd();
    }
}
