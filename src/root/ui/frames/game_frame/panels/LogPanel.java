package root.ui.frames.game_frame.panels;

import root.ui.frames.components.CustomTextArea;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    JTextArea textArea;
    JScrollPane scroll;

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
        scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
    }

}

