package root.ui.frames.game_frame.panels;

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
        textArea = new JTextArea("SidePanel", 5, 17);
        textArea.setOpaque(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setFont(graphicsManager.getSideFont());
        textArea.setWrapStyleWord(true);
        textArea.setBorder(BorderFactory.createEmptyBorder());
        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
    }

}

