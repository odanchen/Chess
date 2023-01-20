package ui.frames.components;

import ui.graphics.GraphicsManager;

import javax.swing.JPanel;
import java.awt.Graphics;

public class BackgroundPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final String fileName;

    public BackgroundPanel(GraphicsManager graphicsManager, String filename) {
        this.graphicsManager = graphicsManager;
        this.fileName = filename;
        this.setBounds(graphicsManager.getBackgroundBounds());
        this.setVisible(true);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(graphicsManager.getBackgroundTexture(fileName), 0, 0, null);
    }
}
