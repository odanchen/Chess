package root.ui.frames.game_frame;

import root.ui.frames.BaseFrame;
import root.ui.frames.game_frame.panels.GamePanel;
import root.ui.graphics.GraphicsManager;
import root.ui.frames.menu_frame.CustomButton;
import root.ui.game_flow.GameControl;
import root.ui.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends BaseFrame {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private final GameControl gameControl;
    private final GamePanel gamePanel;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private void addFlipButton() {
        JButton flipButton = new CustomButton("Flip");
        flipButton.setBackground(graphicsManager.getBlackSquareColor());
        flipButton.setForeground(graphicsManager.getWhiteSquareColor());
        flipButton.setContentAreaFilled(true);
        flipButton.setFont(new Font("Serif", Font.BOLD, 20));
        getContentPane().add(flipButton);
        flipButton.setBounds(graphicsManager.getFlipButtonBounds());
        flipButton.addActionListener(e -> gamePanel.flipPanel());
    }

    public GameFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        super(graphicsManager);
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;
        this.gamePanel = new GamePanel(gameStatus, gameControl, graphicsManager);

        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        getContentPane().add(gamePanel);
        addFlipButton();
    }
}
