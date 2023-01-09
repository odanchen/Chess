
package root.ui.frames.menu_frame;

import root.ui.GameManager;

import javax.swing.*;
import java.awt.*;

public class MenuMainFrame {
    public void MainMenu(GameManager gameManager) {

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

        quitButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor((JComponent) e.getSource()).dispose(); // Closes window
        });

        JFrame f = new JFrame("Chess");
        f.setBounds(gameManager.getGraphicsManager().getMenuBounds());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.add(buttonsPanel);
        f.add(titlePanel);
        f.validate();
    }
}





