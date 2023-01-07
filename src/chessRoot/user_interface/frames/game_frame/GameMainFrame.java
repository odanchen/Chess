package chessRoot.user_interface.frames.game_frame;

import chessRoot.user_interface.GraphicsManager;
import chessRoot.user_interface.frames.custom_components.CustomButton;
import chessRoot.user_interface.game_flow.GameControl;
import chessRoot.user_interface.game_flow.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.nio.file.Paths;

public class GameMainFrame extends JFrame {
    private final GameStatus gameStatus;
    private final GraphicsManager graphicsManager;
    private final GameControl gameControl;
    private final GamePanel gameFrame;
    private String scene;

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
    public Dimension getPreferredSize() {
        return graphicsManager.getGameBounds().getSize();
    }

    public GameMainFrame(GameStatus gameStatus, GameControl gameControl, GraphicsManager graphicsManager) {
        scene = "menu";
        addIcon();
        this.gameStatus = gameStatus;
        this.gameControl = gameControl;
        this.graphicsManager = graphicsManager;
        this.gameFrame = new GamePanel(gameStatus, gameControl, graphicsManager);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myPaint();
    }


    public void myPaint() {
        switch (scene) {
            case "menu":
                showMenu();
                break;
            case "chessGame":
                showChessGame();
                break;
        }
    }

    public void changeScene(String scene) {
        this.scene = scene;
        myPaint();
    }

    private void showChessGame() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        setBounds(graphicsManager.getGameBounds());
        setResizable(false);
        setVisible(true);
        getContentPane().add(gameFrame);
        addFlipButton();
    }

    private void showMenu() {

        JLabel title = new JLabel();
        title.setText("Chess Game");
        title.setFont(new Font("Serif", Font.PLAIN, 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(220, 150, 150, 50);
        titlePanel.add(title);

        // Buttons business
        JButton startButton = new CustomButton("   Start   ");
        JButton settingsButton = new CustomButton("Settings");
        JButton quitButton = new CustomButton("    Quit    ");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(230, 250, 130, 170);
        buttonsPanel.add(startButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(quitButton);

        startButton.addActionListener(e -> {
            closeWindow(e);
            changeScene("chessGame");
        });

        quitButton.addActionListener(this::closeWindow);

        JFrame f = new JFrame("Chess");
        f.setBounds(graphicsManager.getMenuBounds());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GroupLayout layout = new GroupLayout(f.getContentPane());
        f.getContentPane().setLayout(layout);
        f.setVisible(true);
        f.setResizable(false);
        f.add(buttonsPanel);
        f.add(titlePanel);
        f.validate();
    }

    private void closeWindow(ActionEvent e) {
        SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose();
    }
}
