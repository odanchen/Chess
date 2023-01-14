package root.ui.frames.end_frame.panels;

import root.ui.game_flow.GameResult;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class EndMessagePanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameResult gameResult;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public EndMessagePanel(GraphicsManager graphicsManager, GameResult gameResult) {
        this.graphicsManager = graphicsManager;
        this.gameResult = gameResult;
        Dimension size = graphicsManager.getFrameDimension();
        this.setBounds(size.width * 14 / 20, size.height / 4, size.width * 2 / 7, size.height / 4);
    }
}
