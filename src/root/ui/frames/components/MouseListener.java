package root.ui.frames.components;

import root.ui.frames.game_frame.panels.DragPanel;
import root.ui.frames.game_frame.panels.GamePanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseInputAdapter {

    private final GamePanel gamePanel;
    private final DragPanel dragPanel;

    public MouseListener(GamePanel gamePanel, DragPanel dragPanel) {
        super();
        this.gamePanel = gamePanel;
        this.dragPanel = dragPanel;
    }

    public void mousePressed(MouseEvent e) {
        gamePanel.onMousePress(e);
    }
    public void mouseReleased(MouseEvent e) {
        gamePanel.onMouseRelease(e);
        dragPanel.mouseReleased(e);
        gamePanel.setUndrawnPieceNull();
    }
    public void mouseDragged(MouseEvent e) {
        gamePanel.setUndrawnPiece(e);
        dragPanel.onNewMouseEvent(e);
    }

}
