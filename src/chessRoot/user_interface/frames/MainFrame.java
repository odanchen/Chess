package chessRoot.user_interface.frames;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.frames.game_frame.GamePanel;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.FileLockInterruptionException;

public class MainFrame extends JFrame {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private final GameControl gameControl;
    private final GamePanel gameFrame;
    private final JButton flipButton;

    public GamePanel getGameFrame() {
        return gameFrame;
    }

    private void addFlipButton() {
        getContentPane().add(flipButton);
        flipButton.setBounds(graphicsManager.getFlipButtonBounds());
        flipButton.addActionListener(e -> gameFrame.flipPanel());
    }

    public MainFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;
        this.gameFrame = new GamePanel(gameStatus, gameControl, graphicsManager);
        this.flipButton = new JButton("FLIP");

        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        setBounds(graphicsManager.getFrameBounds());
        setResizable(false);
        setVisible(true);
        getContentPane().add(gameFrame);
        addFlipButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
