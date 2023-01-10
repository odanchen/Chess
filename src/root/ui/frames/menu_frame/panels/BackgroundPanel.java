package root.ui.frames.menu_frame.panels;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundPanel extends JPanel {
    private final GraphicsManager graphicsManager;

    @Override
    public void paint(Graphics g) {
        g.drawImage(graphicsManager.getBackgroundTexture("chessMenu"), 0, 0, null);
    }

    public BackgroundPanel(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getBackgroundBounds());
        this.setVisible(true);
        this.setOpaque(false);
    }
}
