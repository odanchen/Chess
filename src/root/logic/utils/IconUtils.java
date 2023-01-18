package root.logic.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

public class IconUtils {
    public IconUtils() {

    }

    public static Icon darken(Icon icon) {
        BufferedImage img = getBufferedImageFromIcon(icon);
        BufferedImageOp op = new RescaleOp(0.9f, 0, null);
        return new ImageIcon(op.filter(img, null));
    }

    public static BufferedImage getBufferedImageFromIcon(Icon icon) {
        BufferedImage buffer = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        icon.paintIcon(new JLabel(), g, 0, 0);
        g.dispose();
        return buffer;
    }
}