
package root.ui.frames.menu_frame;

import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends BaseFrame {

    public MenuFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);

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
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose(); // Closes window
            gameManager.runChess();
        });

        quitButton.addActionListener(e -> System.exit(0));

        add(buttonsPanel);
        add(titlePanel);
        validate();
    }
}





