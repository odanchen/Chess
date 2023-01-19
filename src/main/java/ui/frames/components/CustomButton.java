package ui.frames.components;

import logic.utils.IconUtils;
import ui.graphics.GraphicsManager;

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
        addPhases(graphicsManager.getButtonIcon(id, size));
    }
    private void addPhases(ImageIcon image) {
        setRolloverIcon(IconUtils.darken(image));
        setPressedIcon(IconUtils.darken(IconUtils.darken(image)));
    }


}
