package root.ui.frames.game_frame.panels;

import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerPanel extends JPanel implements ActionListener {

    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;

    public TimerPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getTimerPanelBounds());
        this.setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        Font timerFont = graphicsManager.getTimerFont();
        Rectangle topTimer = new Rectangle(0, 0, graphicsManager.getSquareSize() * 2, graphicsManager.getEdgeSize());
        Rectangle bottomTimer = new Rectangle(0, graphicsManager.getBoardSize() + graphicsManager.getEdgeSize() * 3, graphicsManager.getSquareSize() * 2, graphicsManager.getEdgeSize());

        g.fillRect(topTimer.x, topTimer.y, topTimer.width, topTimer.height);
        g.fillRect(bottomTimer.x, bottomTimer.y, bottomTimer.width, bottomTimer.height);

        g.setColor(Color.BLACK);
        graphicsManager.drawCenteredString(g, (graphicsManager.isFlipped()) ? gameStatus.getWhiteTimeLeft() : gameStatus.getBlackTimeLeft(), topTimer, timerFont);
        graphicsManager.drawCenteredString(g, (graphicsManager.isFlipped()) ? gameStatus.getBlackTimeLeft() : gameStatus.getWhiteTimeLeft(), bottomTimer, timerFont);

        Timer timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
