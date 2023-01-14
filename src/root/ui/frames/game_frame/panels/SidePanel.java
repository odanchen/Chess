package root.ui.frames.game_frame.panels;

import root.ui.frames.components.CustomButton;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;

public class SidePanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GamePanel gamePanel;
    public SidePanel(GraphicsManager graphicsManager, GamePanel gamePanel) {
        this.graphicsManager = graphicsManager;
        this.gamePanel = gamePanel;
        this.setBounds(graphicsManager.getSideBounds());
        this.setOpaque(true);
        this.setVisible(true);
        addFlipButton();
    }

    private void addFlipButton() {
        JButton flipButton = new CustomButton("flipButtonReleased", graphicsManager, graphicsManager.getFlipButtonDimensions());
        add(flipButton);
        flipButton.addActionListener(e -> gamePanel.flipPanel());
    }
}

