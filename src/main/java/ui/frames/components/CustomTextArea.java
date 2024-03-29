package ui.frames.components;

import ui.graphics.GraphicsManager;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Rectangle;

public class CustomTextArea extends JTextArea {
    private final GraphicsManager graphicsManager;

    public CustomTextArea(GraphicsManager graphicsManager, String text, int x, int y) {
        super(text, x, y);
        this.graphicsManager = graphicsManager;
        setOpaque(false);
        setEditable(false);
        setFocusable(false);
        setLineWrap(true);
        setFont(graphicsManager.getSideFont());
        setWrapStyleWord(true);
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Rectangle rect = getVisibleRect();
        g.drawImage(graphicsManager.getBackgroundTexture("gameLogBack"), rect.x, rect.y, rect.width, rect.height, this);
        super.paintComponent(g);
    }
}
