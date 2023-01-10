package root.ui.frames.menu_frame;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String id, GraphicsManager graphicsManager,int width, int height) {
        super(graphicsManager.getImageIconOf(id, width, height));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(width, height));
        setBorderPainted(false);
        setFocusPainted(false);
    }

}
