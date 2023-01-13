package root.ui.frames.game_frame.panels;

import root.logic.pieces.ChessPiece;
import root.logic.pieces.Pawn;
import root.logic.pieces.properties.PieceColor;
import root.logic.pieces.properties.Position;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DragPanel extends JPanel {
    private final GraphicsManager graphicsManager;
    private MouseEvent mouse;
    public DragPanel(GraphicsManager graphicsManager) {
        this.graphicsManager = graphicsManager;
        this.setBounds(graphicsManager.getPlayAreaBounds());
        this.setOpaque(false);
        addMotionListener();
    }

    private void addMotionListener() {
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouse = e;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (mouse != null) drawPiece(g,mouse);
    }

    private void drawPiece(Graphics g, MouseEvent e) {
        int row = e.getY();
        int col = e.getX() - graphicsManager.getEdgeSize();

        BufferedImage image = graphicsManager.getTextureOfPiece(new Pawn(new Position('a',1), PieceColor.BLACK));
        g.drawImage(image, col, row, null);
    }


}
