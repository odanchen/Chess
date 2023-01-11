package root.ui.frames.menu_frame;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String id, GraphicsManager graphicsManager, Dimension size) {
        super(graphicsManager.getImageIconOf(id, size.width, size.height));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(size);
        setBorderPainted(false);
        setFocusPainted(false);
    }
}
