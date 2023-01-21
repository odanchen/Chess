package ui.frames.game_frame.panels.dragging;

import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;


import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;

public class DragPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private final GameStatus gameStatus;
    private final PieceDraggedPanel piecePanel;

    public DragPanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        this.graphicsManager = graphicsManager;
        this.gameStatus = gameStatus;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setVisible(true);
        this.setOpaque(false);
        this.piecePanel = new PieceDraggedPanel(graphicsManager,gameStatus);
        add(piecePanel);
    }

    public void onNewMouseEvent(MouseEvent e) {
        piecePanel.pieceMoved(e);
    }

    public void mouseReleased() {
        piecePanel.clean();
    }

}
