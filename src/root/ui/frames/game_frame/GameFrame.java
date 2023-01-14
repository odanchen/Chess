package root.ui.frames.game_frame;

import root.logic.Board;
import root.logic.log.GameLog;
import root.logic.pieces.properties.PieceColor;
import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.game_frame.panels.GamePanel;
import root.ui.frames.game_frame.panels.SidePanel;
import root.ui.graphics.GraphicsManager;
import root.ui.frames.components.CustomButton;
import root.ui.game_flow.GameStatus;

import javax.swing.*;

public class GameFrame extends BaseFrame {
    private final GraphicsManager graphicsManager;
    private final GamePanel gamePanel;
    private final SidePanel sidePanel;
    private Board createBoard() {
        Board board = new Board();
        board.fillStandardBoard();
        return board;
    }

    public void swapToEndFrame() {
        gameManager.swapToEndFrame(gamePanel.getGameStatus());
        this.dispose();
    }

    public GameFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        this.graphicsManager = graphicsManager;
        GameStatus gameStatus = new GameStatus(createBoard(), PieceColor.WHITE);
        this.gamePanel = new GamePanel(gameStatus, graphicsManager, this);
        this.sidePanel = new SidePanel(gameStatus, graphicsManager, gamePanel);
        getContentPane().add(sidePanel);
        getContentPane().add(gamePanel);
        addBackgroundPanel("gameBackground");
    }
}
