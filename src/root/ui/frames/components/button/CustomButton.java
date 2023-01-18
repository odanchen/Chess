package root.ui.frames.components.button;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

public class CustomButton extends JButton {

    public CustomButton(String id, GraphicsManager graphicsManager, Dimension size) {
        super(graphicsManager.getButtonIcon(id, size));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setPreferredSize(size);
        setBorderPainted(false);
        setFocusPainted(false);
        addPhases(graphicsManager.getButtonIcon(id, size));
    }
    private void addPhases(ImageIcon image) {
        setRolloverIcon(darken(image));
        setPressedIcon(darken(darken(image)));
    }

    public static Icon darken(Icon icon) {
        BufferedImage img = getBufferedImageFromIcon(icon);
        BufferedImageOp op = new RescaleOp(0.9f, 0, null);
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
