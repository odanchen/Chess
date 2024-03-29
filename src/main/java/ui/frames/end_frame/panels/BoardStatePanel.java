package ui.frames.end_frame.panels;

import ui.frames.game_frame.panels.BoardPanel;
import ui.frames.game_frame.panels.PiecePanel;
import ui.game_flow.GameStatus;
import ui.graphics.GraphicsManager;

import javax.swing.JPanel;
import javax.swing.GroupLayout;

public class BoardStatePanel extends JPanel {

    public BoardStatePanel(GraphicsManager graphicsManager, GameStatus gameStatus) {
        setLayout(new GroupLayout(this));
        PiecePanel piecePanel = new PiecePanel(graphicsManager, gameStatus);
        BoardPanel boardPanel = new BoardPanel(graphicsManager);
        this.setBounds(graphicsManager.getGamePanelBounds());
        this.setPreferredSize(graphicsManager.getGamePanelBounds().getSize());
        this.setOpaque(false);
        this.setVisible(true);

        add(piecePanel);
        add(boardPanel);

        validate();
    }
}
