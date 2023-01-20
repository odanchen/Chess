package ui.frames.components;

import ui.GameManager;
import ui.graphics.GraphicsManager;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Taskbar;

public abstract class BaseFrame extends JFrame {
    protected final GraphicsManager graphicsManager;
    protected final GameManager gameManager;

    public BaseFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.gameManager = gameManager;
        addIcon();
        setTitle("Chess");
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        setBounds(graphicsManager.getGameBounds());
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public Dimension getPreferredSize() {
        return graphicsManager.getGameBounds().getSize();
    }

    protected void addBackgroundPanel(String fileName) {
        BackgroundPanel backgroundPanel = new BackgroundPanel(graphicsManager, fileName);
        add(backgroundPanel);
        backgroundPanel.repaint();
    }

    private void addIcon() {
        ImageIcon img = new ImageIcon(graphicsManager.getTextureOfPiece("bk"));
        setIconImage(img.getImage());

        if (System.getProperty("os.name").contains("Mac")) {
            Taskbar.getTaskbar().setIconImage(img.getImage());
        }
    }
}
