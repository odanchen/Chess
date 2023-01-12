package root.ui.frames.components;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String id, GraphicsManager graphicsManager, Dimension size) {
        super(graphicsManager.getButtonIcon(id, size));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(size);
        setBorderPainted(false);
        setFocusPainted(false);
    }
}
