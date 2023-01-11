package root.ui.frames.game_frame;

import root.logic.Board;
import root.logic.pieces.properties.PieceColor;
import root.ui.GameManager;
import root.ui.frames.BaseFrame;
import root.ui.frames.game_frame.panels.GamePanel;
import root.ui.graphics.GraphicsManager;
import root.ui.frames.menu_frame.CustomButton;
import root.ui.game_flow.GameStatus;

import javax.swing.*;

public class GameFrame extends BaseFrame {
    private final GraphicsManager graphicsManager;
    private final GamePanel gamePanel;

    private void addFlipButton() {
        JButton flipButton = new CustomButton("flipButtonReleased", graphicsManager, graphicsManager.getFlipButtonBounds().getSize());
        getContentPane().add(flipButton);
        flipButton.setBounds(graphicsManager.getFlipButtonBounds());
        flipButton.addActionListener(e -> gamePanel.flipPanel());
    }

    private Board createBoard() {
        Board board = new Board();
        board.fillStandardBoard();
        return board;
    }

    public GameFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        this.graphicsManager = graphicsManager;
        GameStatus gameStatus = new GameStatus(createBoard(), PieceColor.WHITE);
        this.gamePanel = new GamePanel(gameStatus, graphicsManager);

        getContentPane().setBackground(graphicsManager.getWhiteSquareColor());
        getContentPane().add(gamePanel);
        addFlipButton();
    }
}
