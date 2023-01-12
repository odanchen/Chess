package root.ui.frames.components;

import root.ui.GameManager;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class BaseFrame extends JFrame {
    protected final GraphicsManager graphicsManager;
    protected final GameManager gameManager;

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
        ImageIcon img = new ImageIcon(String.join(File.separator, graphicsManager.getPathToTexture("bk")));
        setIconImage(img.getImage());

        if (System.getProperty("os.name").contains("Mac")) {
            Taskbar.getTaskbar().setIconImage(img.getImage());
        }
    }

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
}
