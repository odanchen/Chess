package ui.frames.game_frame.panels.dragging;

import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PieceDraggedPanel extends JPanel {

    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;

    public PieceDraggedPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage image = graphicsManager.getTextureOfPiece(gameStatus.getSelectedPiece());
        g.drawImage(image, 0, 0, null);
        super.paint(g);
    }
}
