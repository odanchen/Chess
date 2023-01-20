package ui.frames.game_frame.panels;

import ui.frames.components.CustomTextArea;
import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.*;

public class LogPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    private JTextArea textArea;

    public LogPanel(GameStatus gameStatus, GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getLogPanelBounds());
        this.setOpaque(false);

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
        textArea = new CustomTextArea(graphicsManager, "SidePanel", 5, 20);
        JScrollPane scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
    }
}

