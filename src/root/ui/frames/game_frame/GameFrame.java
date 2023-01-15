package root.ui.frames.game_frame;

import root.logic.Board;
import root.logic.pieces.properties.PieceColor;
import root.ui.GameManager;
import root.ui.frames.components.BaseFrame;
import root.ui.frames.game_frame.panels.ButtonsPanel;
import root.ui.frames.game_frame.panels.GamePanel;
import root.ui.frames.game_frame.panels.SidePanel;
import root.ui.game_flow.GameStatus;
import root.ui.graphics.GraphicsManager;

public class GameFrame extends BaseFrame {
    private final GamePanel gamePanel;
    private final SidePanel sidePanel;
    private final ButtonsPanel buttonsPanel;


    public GameFrame(GameManager gameManager, GraphicsManager graphicsManager) {
        super(gameManager, graphicsManager);
        GameStatus gameStatus = new GameStatus(createBoard(), PieceColor.WHITE);
        this.gamePanel = new GamePanel(gameStatus, graphicsManager, this);
        this.sidePanel = new SidePanel(gameStatus, graphicsManager);
        this.buttonsPanel = new ButtonsPanel(gamePanel, graphicsManager);
        getContentPane().add(sidePanel);
        getContentPane().add(gamePanel);
        getContentPane().add(buttonsPanel);
        addBackgroundPanel("gameBackground");
    }

    private Board createBoard() {
        Board board = new Board();
        board.fillStandardBoard();
        return board;
    }

    public void swapToEndFrame() {
        gameManager.swapToEndFrame(gamePanel.getGameStatus());
        this.dispose();
    }
}
