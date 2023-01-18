package root.ui.frames.components;

import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;

import static root.logic.utils.IconUtils.darken;

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


}
