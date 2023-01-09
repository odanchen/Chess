package root.ui.frames.game_frame;

import root.ui.graphics.GraphicsManager;
import root.ui.frames.menu_frame.CustomButton;
import root.ui.game_flow.GameControl;
import root.ui.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Paths;

public class GameMainFrame extends JFrame {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private final GameControl gameControl;
    private final GamePanel gameFrame;

    public GamePanel getGameFrame() {
        return gameFrame;
    }

    private void addFlipButton() {
        JButton flipButton = new CustomButton("Flip");
        flipButton.setBackground(graphicsManager.getBlackSquareColor());
        flipButton.setForeground(graphicsManager.getWhiteSquareColor());
        flipButton.setContentAreaFilled(true);
        flipButton.setFont(new Font("Serif", Font.BOLD, 20));
        getContentPane().add(flipButton);
        flipButton.setBounds(graphicsManager.getFlipButtonBounds());
        flipButton.addActionListener(e -> gameFrame.flipPanel());
    }

    private void addIcon() {
        ImageIcon img = new ImageIcon(String.join(File.separator, graphicsManager.getPathToTexture("bk")));
        setIconImage(img.getImage());

        if (System.getProperty("os.name").contains("Mac")) {
            Taskbar.getTaskbar().setIconImage(img.getImage());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getGameBounds().getSize();
    }

    public GameMainFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;
        this.gameFrame = new GamePanel(gameStatus, gameControl, graphicsManager);
        addIcon();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        setBounds(graphicsManager.getGameBounds());
        setResizable(false);
        setVisible(true);
        getContentPane().add(gameFrame);
        addFlipButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
