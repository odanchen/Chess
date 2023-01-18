package root.ui.frames.components.button;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

public class ButtonListener extends MouseInputAdapter{
    private final CustomButton button;
    private final Icon originalIcon;

    public ButtonListener(CustomButton button, Icon icon) {
        super();
        this.button = button;
        this.originalIcon = icon;
    }

    public void mouseEntered(MouseEvent e) {
        button.setIcon(darken(originalIcon));
    }

    public void mouseExited(MouseEvent e) {
        button.setIcon(originalIcon);
    }



    public static Icon darken(Icon icon) {
        BufferedImage img = getBufferedImageFromIcon(icon);
        BufferedImageOp op = new RescaleOp(0.75f, 0, null);
        return new ImageIcon(op.filter(img, null));
    }
    public static BufferedImage getBufferedImageFromIcon(Icon icon) {
        BufferedImage buffer = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        icon.paintIcon(new JLabel(), g, 0, 0);
        g.dispose();
        return buffer;
    }


}
