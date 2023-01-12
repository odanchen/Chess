package root.ui.frames.components;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final String fileName;

    @Override
    public void paint(Graphics g) {
        g.drawImage(graphicsManager.getBackgroundTexture(fileName), 0, 0, null);
    }

    public BackgroundPanel(GraphicsManager graphicsManager, String filename) {
        this.graphicsManager = graphicsManager;
        this.fileName = filename;
        this.setBounds(graphicsManager.getBackgroundBounds());
        this.setVisible(true);
        this.setOpaque(false);
    }
}
