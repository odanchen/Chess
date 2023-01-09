package chessRoot.user_interface.frames.menu_frame;

import javax.swing.*;
import chessRoot.user_interface.GameManager;
import java.awt.Component;
public class SettingsFrame {
    public void settingsScreen(GameManager gameManager) {
        JFrame mainSettingsFrame = new JFrame("Settings");
        mainSettingsFrame.setBounds(gameManager.getGraphicsManager().getMenuBounds());
        mainSettingsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainSettingsFrame.setResizable(false);
        mainSettingsFrame.validate();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));


        mainSettingsFrame.add(panel);


        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lbl);

        String[] choices = { "Cburnett", "Kilifiger", "Kosal", "Legipzig",
                "Maya", "Pirat", "Regular" };

        final JComboBox<String> cb = new JComboBox<>(choices);
        //cb.setPrototypeDisplayValue();
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cb);

        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btn);
        mainSettingsFrame.setVisible(true);

        JColorChooser darkColor = new JColorChooser();
        panel.add(darkColor);




    }
}
