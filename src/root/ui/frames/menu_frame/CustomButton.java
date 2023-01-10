package root.ui.frames.menu_frame;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {


    public CustomButton(String id, GraphicsManager graphicsManager) {
        super(graphicsManager.getImageIconOf(id));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(new Dimension(150, 50));
        setBorderPainted(false);
        setFocusPainted(false);
    }


}
