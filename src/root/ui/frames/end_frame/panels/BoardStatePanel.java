package root.ui.frames.end_frame.panels;

import root.ui.frames.game_frame.panels.BoardPanel;
import root.ui.frames.game_frame.panels.PiecePanel;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

import javax.swing.*;
import java.awt.*;

public class BoardStatePanel extends JPanel {

    public BoardStatePanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        setLayout(new GroupLayout(this));
        Dimension panelSize = graphicsManager.getBoardPanelBounds().getSize();
        PiecePanel piecePanel = new PiecePanel(graphicsManager, gameStatus);
        BoardPanel boardPanel = new BoardPanel(graphicsManager);
        this.setBounds(0, 0, panelSize.width, panelSize.height);
        this.setPreferredSize(panelSize);
        this.setOpaque(false);
        this.setVisible(true);

        add(piecePanel);
        add(boardPanel);

        validate();
    }
}
