
package root.ui.frames.menu_frame;

import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.frames.menu_frame.panels.BackgroundPanel;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends BaseFrame {

    public MenuFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);

        // Buttons business
        JButton startButton = new CustomButton("   Start   ");
        JButton settingsButton = new CustomButton("Settings");
        JButton quitButton = new CustomButton("    Quit    ");
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(230, 250, 130, 170);
        buttonsPanel.add(startButton);
        buttonsPanel.add(settingsButton);
        buttonsPanel.add(quitButton);

        BackgroundPanel backgroundPanel = new BackgroundPanel(graphicsManager);


        startButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose(); // Closes window
            gameManager.runChess();
        });

        quitButton.addActionListener(e -> System.exit(0));



        add(buttonsPanel);
        add(backgroundPanel);

        backgroundPanel.repaint();
        validate();
    }
}





