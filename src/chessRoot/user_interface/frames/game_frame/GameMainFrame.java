package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStatus;

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
        JButton flipButton = new JButton("FLIP");
        getContentPane().add(flipButton);
        flipButton.setBounds(graphicsManager.getFlipButtonBounds());
        flipButton.addActionListener(e -> gameFrame.flipPanel());
    }

    private void addIcon() {
        String root = Paths.get("").toAbsolutePath().toString();
        String[] fullPath = {root, "src", "chessRoot", "assets", "pieces_textures", "cburnett", "bk.png"};
        ImageIcon img = new ImageIcon(String.join(File.separator, fullPath));
        setIconImage(img.getImage());
        if (System.getProperty("os.name").contains("Mac")) {
            Taskbar.getTaskbar().setIconImage(img.getImage());
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return graphicsManager.getFrameBounds().getSize();
    }

    public GameMainFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        addIcon();

        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;
        this.gameFrame = new GamePanel(gameStatus, gameControl, graphicsManager);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        setBounds(graphicsManager.getFrameBounds());
        setResizable(false);
        setVisible(true);
        getContentPane().add(gameFrame);
        addFlipButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
