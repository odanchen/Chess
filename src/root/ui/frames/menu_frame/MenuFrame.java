
package root.ui.frames.menu_frame;

import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.frames.menu_frame.panels.BackgroundPanel;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends BaseFrame {

    private void addButtonsPanel() {
        JPanel buttonsPanel = new JPanel();

        Dimension buttonSize = graphicsManager.getTextButtonDimension();
        int buttonPanelHeight = buttonSize.height * 4;
        JButton startButton = new CustomButton("startButtonReleased", graphicsManager, buttonSize);
        JButton settingsButton = new CustomButton("settingsButtonReleased", graphicsManager, buttonSize);
        JButton quitButton = new CustomButton("quitButtonReleased", graphicsManager, buttonSize);

        buttonsPanel.setBounds(graphicsManager.getCenterOfScreenX(buttonSize.width), graphicsManager.getCenterOfScreenY(buttonPanelHeight), buttonSize.width, buttonPanelHeight);
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(startButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(quitButton);

        startButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose(); // Closes window
            gameManager.runChess();
        });
        quitButton.addActionListener(e -> System.exit(0));

        add(buttonsPanel);
    }

    private void addBackgroundPanel() {
        BackgroundPanel backgroundPanel = new BackgroundPanel(graphicsManager);
        add(backgroundPanel);
        backgroundPanel.repaint();
    }

    public MenuFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        addButtonsPanel();
        addBackgroundPanel();
        validate();
    }
}





