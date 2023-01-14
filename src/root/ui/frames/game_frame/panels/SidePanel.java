package root.ui.frames.game_frame.panels;

import root.ui.frames.components.CustomButton;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GamePanel gamePanel;
    private final GameStatus gameStatus;
    JTextArea textArea;
    JScrollPane scroll;
    public SidePanel(GameStatus gameStatus, GraphicsManager graphicsManager, GamePanel gamePanel) {
        this.graphicsManager = graphicsManager;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getSideBounds());
        this.setOpaque(false);
        addFlipButton();
        addTextAreaParams();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        updateTextArea();
    }

    public void updateTextArea() {
        textArea.setText(gameStatus.getGameLog().getFullString());
    }

    private void addTextAreaParams() {
        textArea = new JTextArea("SidePanel", 5, 17);
        textArea.setOpaque(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setFont(graphicsManager.getSideFont());
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder());
        scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
    }

    private void addFlipButton() {
        JButton flipButton = new CustomButton("flipButtonReleased", graphicsManager, graphicsManager.getFlipButtonDimensions());
        add(flipButton);
        flipButton.addActionListener(e -> gamePanel.flipPanel());
    }
}

