package root.ui.frames.menu_frame;

import javax.swing.*;
import java.awt.*;

import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.frames.menu_frame.panels.BackgroundPanel;
import root.ui.graphics.GraphicsManager;
public class SettingsFrame extends BaseFrame {
    public SettingsFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);

        JPanel panel = new JPanel();
        panel.setBounds(graphicsManager.getGameBounds().height/4,graphicsManager.getGameBounds().width/4,graphicsManager.getGameBounds().width/2,170);
        //panel.setBackground(Color.blue);
        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lbl);

        String[] choices = { "Cburnett", "Kilifiger", "Kosal", "Legipzig",
                "Maya", "Pirat", "Regular" };



        final JComboBox<String> cb = new JComboBox<>(choices);
        cb.setSelectedItem(choices[2]);
        cb.setMaximumSize(cb.getPreferredSize());
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(cb);

        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btn);
        setVisible(true);



        add(panel);

    }
}

