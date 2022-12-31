package chessRoot.user_interface.frames;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.frames.game_frame.GamePanel;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;

public class MainFrame extends JFrame {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private final GameControl gameControl;
    private final GamePanel gameFrame;

    public GamePanel getGameFrame() {
        return gameFrame;
    }

    public MainFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;
        this.gameFrame = new GamePanel(gameStatus, gameControl, graphicsManager);


        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        setBounds(graphicsManager.getFrameBounds());
        setResizable(false);
        setVisible(true);
        getContentPane().add(gameFrame);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
